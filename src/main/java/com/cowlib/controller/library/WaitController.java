package com.cowlib.controller.library;

import com.cowlib.model.Wait;
import com.cowlib.repository.WaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/libs/{ownerId}/wait")
public class WaitController {

    @Autowired
    WaitRepository waitRepository;

    @PostMapping
    public void wait(Wait wait) {
        wait.setStatus("BORROW");
        waitRepository.insert(wait);
    }

    @DeleteMapping
    public void cancel(Wait wait) {
        wait.setStatus("CANCEL");
        waitRepository.update(wait);
    }
}
