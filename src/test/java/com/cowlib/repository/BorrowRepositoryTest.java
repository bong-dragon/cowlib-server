package com.cowlib.repository;

import com.Application;
import com.cowlib.model.Borrow;
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
public class BorrowRepositoryTest {

    @Autowired
    private BorrowRepository borrowRepository;

    @Test
    public void insert() {
        // Given
        Borrow borrow = createSampleBorrow();

        // When
        int key = borrowRepository.insert(borrow);

        // Then
        borrow.setId(key);
        Borrow inserted = borrowRepository.select(borrow);
        assertThat(inserted).isEqualTo(borrow);

        // After
        borrowRepository.delete(inserted);
    }

    @Test
    public void select() {
        // Given
        Borrow borrow = createSampleBorrow();
        int key = borrowRepository.insert(borrow);

        // When
        borrow.setId(key);
        Borrow inserted = borrowRepository.select(borrow);

        // Then
        assertThat(inserted).isEqualTo(borrow);

        // After
        borrowRepository.delete(borrow);
    }

    @Test
    public void delete() {
        // Given
        Borrow borrow = createSampleBorrow();
        int key = borrowRepository.insert(borrow);

        // When
        borrow.setId(key);
        borrowRepository.delete(borrow);

        // Then
        Borrow deleted = borrowRepository.select(borrow);
        assertThat(deleted).isNull();
    }

    @Test
    public void update() {
        // Given
        Borrow borrow = createSampleBorrow();
        int key = borrowRepository.insert(borrow);

        // When
        borrow.setId(key);
        borrow.setStatus("HOME");
        borrowRepository.update(borrow);

        // Then
        Borrow updated = borrowRepository.select(borrow);
        assertThat(updated.getStatus()).isEqualTo("HOME");
    }

    private Borrow createSampleBorrow() {
        Borrow borrow = new Borrow();
        borrow.setBorrowerId(1);
        borrow.setCallNumberId(1);
        borrow.setStatus("BORROW");
        return borrow;
    }

}
