package com.cowlib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cowlib.code.BorrowStatus;
import com.cowlib.model.Borrow;
import com.cowlib.repository.BorrowRepository;

@RestController
@RequestMapping("/v1/libs/{ownerId}/borrow")
public class BorrowController {

    @Autowired
    BorrowRepository borrowRepository;

    @PostMapping
    public void borrow(Borrow borrow) {
        borrow.setStatus(BorrowStatus.빌려줌.getCode());
        borrowRepository.insert(borrow);
    }

    @DeleteMapping
    public void returnBook(Borrow borrow) {
        borrow.setStatus(BorrowStatus.반납함.getCode());
        borrowRepository.update(borrow);
    }
}
