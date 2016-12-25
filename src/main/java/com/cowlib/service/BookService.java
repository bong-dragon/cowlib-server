package com.cowlib.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cowlib.code.BorrowStatus;
import com.cowlib.code.ReserveStatus;
import com.cowlib.model.Book;
import com.cowlib.model.BookMeta;
import com.cowlib.model.Borrow;
import com.cowlib.model.CallNumber;
import com.cowlib.model.Reserve;
import com.cowlib.model.User;
import com.cowlib.repository.BookMetaRepository;
import com.cowlib.repository.BorrowRepository;
import com.cowlib.repository.CallNumberRepository;
import com.cowlib.repository.ReserveRepository;
import com.cowlib.repository.UserRepository;

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
            book.setCallNumber(callNumber);

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
        List<User> reservers = new ArrayList<>();
        if (reserves.isEmpty()) {
            return reservers;
        }

        for (Reserve reserve : reserves) {
            User reserver = userRepository.selectById(reserve.getReserverId());
            if (reserver == null) continue;
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
