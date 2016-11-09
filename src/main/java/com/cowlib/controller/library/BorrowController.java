package com.cowlib.controller.library;

import com.cowlib.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/libs/{ownerId}/borrow")
public class BorrowController {

    @Autowired
    BorrowRepository borrowRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void borrow(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String borrowerId) {
        borrowRepository.insert(ownerId, bookId, borrowerId);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void returnBook(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String borrowerId) {
        borrowRepository.update(ownerId, bookId, borrowerId, "return");
    }
}
