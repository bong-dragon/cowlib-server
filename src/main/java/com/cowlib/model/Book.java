package com.cowlib.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private BookMeta bookMeta;
    private List<Borrow> borrows;
    private List<Wait> waits;

}
