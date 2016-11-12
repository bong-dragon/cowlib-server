package com.cowlib.repository;

import com.Application;
import com.cowlib.model.Book;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("/application-local.properties")
@Sql({"classpath:drop-tables.sql", "classpath:init.sql"})
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @After
    public void after() {
        Book book = createSampleBook();
        bookRepository.delete(book);
    }

    @Test
    public void insert() {
        // Given
        Book book = createSampleBook();

        // When
        bookRepository.insert(book);

        // Then
        Book inserted = bookRepository.select(book);
        assertThat(inserted).isEqualTo(book);
    }

    @Test
    public void select() {
        // Given
        Book book = createSampleBook();
        bookRepository.insert(book);

        // When
        Book select = bookRepository.select(book);


        // Then
        assertThat(select).isNotNull();
    }

    @Test
    public void delete() {
        // Given
        Book book = createSampleBook();
        bookRepository.insert(book);

        // When
        bookRepository.delete(book);

        // Then
        Book deleted = bookRepository.select(book);
        assertThat(deleted).isNull();
    }

    private Book createSampleBook() {
        Book book = new Book();
        book.setIsbn("isbn");
        book.setIsbn13("isbn13");
        book.setCoverUrl("coverUrl");
        book.setDescription("description");
        book.setPublisher("publisher");
        book.setTitle("title");
        book.setAuthor("author");
        return book;
    }

}
