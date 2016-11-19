package com.cowlib.model;

import lombok.Data;

@Data
public class BookMeta {
    private int id;
    private String isbn;
    private String isbn13;
    private String title;
    private String author;
    private String description;
    private String publisher;
    private String coverUrl;
}
