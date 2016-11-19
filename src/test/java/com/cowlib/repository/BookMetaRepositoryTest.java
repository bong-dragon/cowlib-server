package com.cowlib.repository;

import com.Application;
import com.cowlib.model.BookMeta;
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
@Sql({"classpath:drop_tables.sql", "classpath:create_tables.sql", "classpath:insert_test_data.sql"})
public class BookMetaRepositoryTest {

    @Autowired
    private BookMetaRepository bookMetaRepository;

    @After
    public void after() {
        BookMeta bookMeta = createSampleBook();
        bookMetaRepository.delete(bookMeta);
    }

    @Test
    public void insert() {
        // Given
        BookMeta bookMeta = createSampleBook();

        // When
        bookMetaRepository.insert(bookMeta);

        // Then
        BookMeta inserted = bookMetaRepository.select(bookMeta);
        assertThat(inserted).isEqualTo(bookMeta);
    }

    @Test
    public void select() {
        // Given
        BookMeta bookMeta = createSampleBook();
        bookMetaRepository.insert(bookMeta);

        // When
        BookMeta select = bookMetaRepository.select(bookMeta);

        // Then
        assertThat(select).isNotNull();
    }

    @Test
    public void selectById() {
        // When
        BookMeta select = bookMetaRepository.selectById(1);

        // Then
        assertThat(select.getIsbn()).isEqualTo("8931448287");
    }

    @Test
    public void delete() {
        // Given
        BookMeta bookMeta = createSampleBook();
        bookMetaRepository.insert(bookMeta);

        // When
        bookMetaRepository.delete(bookMeta);

        // Then
        BookMeta deleted = bookMetaRepository.select(bookMeta);
        assertThat(deleted).isNull();
    }

    private BookMeta createSampleBook() {
        BookMeta bookMeta = new BookMeta();
        bookMeta.setIsbn("isbn");
        bookMeta.setIsbn13("isbn13");
        bookMeta.setCoverUrl("coverUrl");
        bookMeta.setDescription("description");
        bookMeta.setPublisher("publisher");
        bookMeta.setTitle("title");
        bookMeta.setAuthor("author");
        return bookMeta;
    }

}
