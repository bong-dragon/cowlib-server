package com.cowlib.controller;

import com.cowlib.User;
import com.cowlib.client.DaumBookApiClient;
import com.cowlib.model.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookController {

    @RequestMapping("books/search")
    public List<Book> greeting(@RequestParam(value = "q", defaultValue = "World") String q) {
        DaumBookApiClient client = new DaumBookApiClient();
        List<Book> books = client.search(q);

        return books;
    }
}
