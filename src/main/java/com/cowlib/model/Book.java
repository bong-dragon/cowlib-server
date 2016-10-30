package com.cowlib.model;


public class Book {
    String isbn;
    String isbn13;
    String title;
    String author;
    String description;
    String publisher;
    String coverUrl;

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public Book setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getCover_url() {
        return coverUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", cover_url='" + coverUrl + '\'' +
                '}';
    }

    public Book setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }


}
