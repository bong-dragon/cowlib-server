package com.cowlib.controller.library;

import com.cowlib.repository.WaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/libs/{ownerId}/wait")
public class WaitController {

    @Autowired
    WaitRepository waitRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void wait(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String waiterId) {
        waitRepository.insert(ownerId, bookId, waiterId);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void cancel(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String waiterId) {
        waitRepository.update(ownerId, bookId, waiterId, "cancel");
    }
}
