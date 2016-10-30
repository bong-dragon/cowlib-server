package com.cowlib.controller;

import com.cowlib.client.DaumBookClient;
import com.cowlib.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    DaumBookClient client;

    @RequestMapping("books/search")
    public List<Book> search(@RequestParam(value = "q") String q) {
        List<Book> books = client.search(q);

        return books;
    }
}
