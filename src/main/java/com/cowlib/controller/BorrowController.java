package com.cowlib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cowlib.code.BorrowStatus;
import com.cowlib.model.Borrow;
import com.cowlib.repository.BorrowRepository;

@RestController
@RequestMapping("/v1/callNumbers/{callNumberId}/borrow")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @PostMapping
    public Borrow borrow(@PathVariable("callNumberId") int callNumberId, Borrow borrow) {
        borrow.setCallNumberId(callNumberId);
        borrow.setStatus(BorrowStatus.빌려줌.getCode());
        borrowRepository.insert(borrow);
        return borrow;
    }

    @DeleteMapping
    public Borrow returnBook(@PathVariable("callNumberId") int callNumberId, Borrow borrow) {
        borrow.setCallNumberId(callNumberId);
        borrow.setStatus(BorrowStatus.반납함.getCode());
        borrowRepository.update(borrow);
        return borrow;
    }
}
