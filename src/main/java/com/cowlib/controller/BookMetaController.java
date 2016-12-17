package com.cowlib.controller;

import com.cowlib.client.DaumBookClient;
import com.cowlib.model.BookMeta;
import com.cowlib.model.BookMetaSearch;
import com.cowlib.model.BookMetaSearchResult;
import com.cowlib.repository.BookMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/v1/bookMetas/search")
public class BookMetaController {

    @Autowired
    private DaumBookClient client;

    @Autowired
    private BookMetaRepository bookMetaRepository;

    @GetMapping
    public BookMetaSearchResult search(BookMetaSearch search) {
        BookMetaSearchResult searchResult = client.search(search);
        List<BookMeta> saved = new ArrayList<>();
        for (BookMeta bookMeta : searchResult.getBookMetas()) {
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

        // 검색 결과에 있는 중복된 내용 제거
        List<BookMeta> uniqueBookMetas = new ArrayList<>(new HashSet<>(saved));

        searchResult.setBookMetas(uniqueBookMetas);
        return searchResult;
    }

    private BookMeta findBookMeta(BookMeta bookMeta) {
        if(bookMeta.isEmptyIsbn13()){
            return bookMetaRepository.selectByIsbn(bookMeta);
        } else {
            return bookMetaRepository.selectByIsbn13(bookMeta);
        }
    }
}
