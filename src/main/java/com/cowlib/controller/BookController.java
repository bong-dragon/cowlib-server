package com.cowlib.controller;

import com.cowlib.model.Book;
import com.cowlib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    public List<Book> getByOnwerId(@RequestParam(value = "ownerId") int ownerId) {
        return service.findByOwnerId(ownerId);
    }
}
