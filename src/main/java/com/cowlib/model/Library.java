package com.cowlib.model;

import lombok.Data;

import java.util.List;

@Data
public class Library {
    User owner;
    List<Book> books;

    public Library(User owner, List<Book> books) {
        this.owner = owner;
        this.books = books;
    }
}
