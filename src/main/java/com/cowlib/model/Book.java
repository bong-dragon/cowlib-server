package com.cowlib.model;

import lombok.Data;

@Data
public class Book {
    String isbn;
    String isbn13;
    String title;
    String author;
    String description;
    String publisher;
    String coverUrl;

}
