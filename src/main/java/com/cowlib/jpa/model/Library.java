package com.cowlib.jpa.model;

import java.util.List;

import com.cowlib.model.Book;
import lombok.Data;

@Data
public class Library {
	List<Book> books;
}
