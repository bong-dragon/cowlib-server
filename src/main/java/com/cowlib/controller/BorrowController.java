package com.cowlib.controller;

import com.cowlib.exception.AlreadyBorrowedCallNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cowlib.code.BorrowStatus;
import com.cowlib.model.Borrow;
import com.cowlib.repository.BorrowRepository;

@RestController
@RequestMapping("/v1/callNumbers/{callNumberId}/borrow")
public class BorrowController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BorrowRepository borrowRepository;

    @PostMapping
    public Borrow borrow(Borrow borrow) {
        borrow.setStatus(BorrowStatus.빌려줌.getCode());
        Borrow alreadyBorrowed = borrowRepository.selectByCallNumberIdAndStatus(borrow);

        if (alreadyBorrowed != null){
            throw new AlreadyBorrowedCallNumberException("already_borrowed=" + alreadyBorrowed);
        }
        borrowRepository.insert(borrow);
        return borrow;
    }

    @DeleteMapping
    public Borrow returnBook(Borrow borrow) {
        borrow.setStatus(BorrowStatus.반납함.getCode());
        borrowRepository.update(borrow);
        return borrow;
    }
}
