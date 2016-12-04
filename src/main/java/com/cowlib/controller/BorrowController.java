package com.cowlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cowlib.code.BorrowStatus;
import com.cowlib.code.ReserveStatus;
import com.cowlib.exception.AlreadyBorrowedCallNumberException;
import com.cowlib.exception.NotBorrowedCallNumberException;
import com.cowlib.model.Borrow;
import com.cowlib.model.Reserve;
import com.cowlib.repository.BorrowRepository;
import com.cowlib.repository.ReserveRepository;

@RestController
@RequestMapping("/v1/callNumbers/{callNumberId}/borrow")
public class BorrowController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping
    public Borrow borrow(Borrow borrow) {
        borrow.setStatus(BorrowStatus.빌려줌.getCode());
        Borrow alreadyBorrowed = borrowRepository.selectByCallNumberIdAndStatus(borrow);

        if (alreadyBorrowed != null){
            throw new AlreadyBorrowedCallNumberException("already_borrowed=" + alreadyBorrowed);
        }

        Reserve reserve = new Reserve();
        reserve.setCallNumberId(borrow.getCallNumberId());
        reserve.setReserverId(borrow.getBorrowerId());
        reserve.setStatus(ReserveStatus.예약함.getCode());

        reserve = reserveRepository.selectByCallNumberIdAndReserverIdAndStatus(reserve);
        reserve.setStatus(ReserveStatus.책빌림.getCode());
        reserveRepository.update(reserve);

         borrowRepository.insert(borrow);
        return borrow;
    }

    @DeleteMapping
    public Borrow returnBook(Borrow borrow) {
        borrow.setStatus(BorrowStatus.빌려줌.getCode());
        Borrow borrowed = borrowRepository.selectByCallNumberIdAndStatus(borrow);

        if (borrowed == null){
            throw new NotBorrowedCallNumberException("not_borrowed=" + borrow);
        }

        borrowed.setStatus(BorrowStatus.반납함.getCode());
        borrowRepository.update(borrowed);
        return borrow;
    }
}
