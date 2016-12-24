package com.cowlib.controller;

import com.cowlib.model.Book;
import com.cowlib.model.Library;
import com.cowlib.model.User;
import com.cowlib.repository.UserRepository;
import com.cowlib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/libraries/{ownerId}")
public class LibraryController {

    @Autowired
    private BookService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Library getByOnwerId(@PathVariable(value = "ownerId") int ownerId) {
        User user = userRepository.selectById(ownerId);
        List<Book> books = service.findByOwnerId(ownerId);

        return new Library(user, books);
    }
}
