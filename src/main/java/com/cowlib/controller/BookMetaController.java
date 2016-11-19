package com.cowlib.controller;

import com.cowlib.client.DaumBookClient;
import com.cowlib.model.BookMeta;
import com.cowlib.repository.BookMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/bookMetas/search")
public class BookMetaController {

    @Autowired
    private DaumBookClient client;

    @Autowired
    private BookMetaRepository bookMetaRepository;

    @GetMapping
    public List<BookMeta> search(@RequestParam(value = "q") String q) {
        List<BookMeta> bookMetas = client.search(q);

        for (BookMeta bookMeta : bookMetas) {
            BookMeta alreaySaved = bookMetaRepository.select(bookMeta);

            if (alreaySaved == null) {
                bookMetaRepository.insert(bookMeta);
            }
        }
        return bookMetas;
    }
}
