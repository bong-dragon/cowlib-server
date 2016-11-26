package com.cowlib.controller;

import com.cowlib.client.DaumBookClient;
import com.cowlib.model.BookMeta;
import com.cowlib.repository.BookMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<BookMeta> searched = client.search(q);
        List<BookMeta> saved = new ArrayList<>();
        for (BookMeta bookMeta : searched) {
            if(bookMeta.isEmptyIsbns()){
                continue;
            }
            BookMeta alreadySaved = findBookMeta(bookMeta);

            if (alreadySaved != null) {
                saved.add(alreadySaved);
            } else {
                bookMetaRepository.insert(bookMeta);
                saved.add(bookMeta);
            }
        }
        return saved;
    }

    private BookMeta findBookMeta(BookMeta bookMeta) {
        if(bookMeta.isEmptyIsbn13()){
            return bookMetaRepository.selectByIsbn(bookMeta);
        } else {
            return bookMetaRepository.selectByIsbn13(bookMeta);
        }
    }
}
