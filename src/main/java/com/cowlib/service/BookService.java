package com.cowlib.service;


import com.cowlib.code.BorrowStatus;
import com.cowlib.code.ReserveStatus;
import com.cowlib.model.*;
import com.cowlib.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private CallNumberRepository callNumberRepository;

    @Autowired
    private BookMetaRepository bookMetaRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Book> findByOwnerId(int ownerId) {

        List<CallNumber> callNumbers = callNumberRepository.selectByOwnerId(ownerId);

        List<Book> books = new ArrayList<>();
        for (CallNumber callNumber : callNumbers) {
            Book book = new Book();

            BookMeta bookMeta = bookMetaRepository.selectById(callNumber.getBookMetaId());
            book.setBookMeta(bookMeta);

            User borrower = findBorrower(callNumber);
            book.setBorrower(borrower);

            List<User> reservers = findReservers(callNumber);
            book.setReservers(reservers);

            books.add(book);
        }
        return books;
    }

    private List<User> findReservers(CallNumber callNumber) {
        Reserve query = new Reserve();
        query.setCallNumberId(callNumber.getId());
        query.setStatus(ReserveStatus.예약함.getCode());

        List<Reserve> reserves = reserveRepository.selectByCallNumberIdAndStatus(query);

        if (reserves.isEmpty()) {
            return null;
        }

        List<User> reservers = new ArrayList<>();
        for (Reserve reserve : reserves) {
            User reserver = userRepository.selectById(reserve.getReserverId());
            reservers.add(reserver);
        }
        return reservers;
    }

    private User findBorrower(CallNumber callNumber) {
        Borrow query = new Borrow();
        query.setCallNumberId(callNumber.getId());
        query.setStatus(BorrowStatus.빌려줌.getCode());

        Borrow borrow = borrowRepository.selectByCallNumberIdAndStatus(query);

        if (borrow == null) {
            return null;
        }
        return userRepository.selectById(borrow.getBorrowerId());
    }
}
