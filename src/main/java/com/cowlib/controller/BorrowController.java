package com.cowlib.controller;

import com.cowlib.model.Borrow;
import com.cowlib.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/libs/{ownerId}/borrow")
public class BorrowController {

    @Autowired
    BorrowRepository borrowRepository;

    @PostMapping
    public void borrow(Borrow borrow) {
        borrow.setStatus("BORROW");
        borrowRepository.insert(borrow);
    }

    @DeleteMapping
    public void returnBook(Borrow borrow) {
        borrow.setStatus("HOME");
        borrowRepository.update(borrow);
    }
}
