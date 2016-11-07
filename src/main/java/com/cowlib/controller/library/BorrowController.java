package com.cowlib.controller.library;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/libs/{ownerId}/borrow")
public class BorrowController {

    @RequestMapping(method = RequestMethod.POST)
    public void borrow(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String borrowerId) {

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void returnBook(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String borrowerId) {

    }
}
