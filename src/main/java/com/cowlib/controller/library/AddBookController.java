package com.cowlib.controller.library;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/libs/{ownerId}/addBook")
public class AddBookController {

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@PathVariable String ownerId, @RequestParam String bookId) {

    }

}
