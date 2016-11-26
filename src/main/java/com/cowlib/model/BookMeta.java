package com.cowlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.StringUtils;

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

    @JsonIgnore
    public boolean isEmptyIsbns() {
        return StringUtils.isEmpty(isbn) && StringUtils.isEmpty(isbn13);
    }

    @JsonIgnore
    public boolean isEmptyIsbn13() {
        return StringUtils.isEmpty(isbn13);
    }
}
