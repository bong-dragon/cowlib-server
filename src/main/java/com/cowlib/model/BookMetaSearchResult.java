package com.cowlib.model;

import lombok.Data;

import java.util.List;

@Data
public class BookMetaSearchResult {

    List<BookMeta> bookMetas;
    int totalCount;

    public BookMetaSearchResult(List<BookMeta> bookMetas, int totalCount){
        this.bookMetas = bookMetas;
        this.totalCount = totalCount;
    }


}
