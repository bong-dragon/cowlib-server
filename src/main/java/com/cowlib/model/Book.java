package com.cowlib.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    private BookMeta bookMeta;
    private CallNumber callNumber;
    private User borrower;
    private List<User> reservers;
}
