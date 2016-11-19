package com.cowlib.service;


import com.cowlib.model.*;
import com.cowlib.repository.BookMetaRepository;
import com.cowlib.repository.BorrowRepository;
import com.cowlib.repository.CallNumberRepository;
import com.cowlib.repository.WaitRepository;
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
    private WaitRepository waitRepository;


    public List<Book> findByOwnerId(int ownerId) {

        List<CallNumber> callNumbers = callNumberRepository.selectByOwnerId(ownerId);

        List<Book> books = new ArrayList<>();
        for (CallNumber callNumber : callNumbers) {
            Book book = new Book();

            BookMeta bookMeta = bookMetaRepository.selectById(callNumber.getBookMetaId());
            List<Borrow> borrows = borrowRepository.selectByCallNumberId(callNumber.getBookMetaId());
            List<Wait> waits = waitRepository.selectByCallNumberId(callNumber.getBookMetaId());

            book.setBookMeta(bookMeta);
            book.setBorrows(borrows);
            book.setWaits(waits);
            books.add(book);
        }
        return books;
    }
}
