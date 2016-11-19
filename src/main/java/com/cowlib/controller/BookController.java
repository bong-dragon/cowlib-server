package com.cowlib.controller;

import com.cowlib.client.DaumBookClient;
import com.cowlib.model.Book;
import com.cowlib.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/books/search")
public class BookController {

    @Autowired
    private DaumBookClient client;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public List<Book> search(@RequestParam(value = "q") String q) {
        List<Book> books = client.search(q);

        for (Book book : books) {
            Book alreaySaved = bookRepository.select(book);

            if (alreaySaved == null) {
                bookRepository.insert(book);
            }
        }
        return books;
    }
}
