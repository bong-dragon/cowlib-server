package com.cowlib.controller.library;

import com.cowlib.repository.UserHasBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/libs/{ownerId}/addBook")
public class AddBookController {

    @Autowired
    private UserHasBookRepository userHasBookRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@PathVariable String ownerId, @RequestParam String bookId) {
        userHasBookRepository.insert(ownerId, bookId);
    }

}
