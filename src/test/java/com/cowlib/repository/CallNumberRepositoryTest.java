package com.cowlib.repository;

import com.Application;
import com.cowlib.model.CallNumber;
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
public class CallNumberRepositoryTest {

    @Autowired
    private CallNumberRepository callNumberRepository;

    @Test
    public void insert() {
        // Given
        CallNumber callNumber = createSampleCallNumer();

        // When
        int key = callNumberRepository.insert(callNumber);

        // Then
        callNumber.setId(key);
        CallNumber inserted = callNumberRepository.select(callNumber);
        assertThat(inserted).isEqualTo(callNumber);

        // After
        callNumberRepository.delete(inserted);
    }

    @Test
    public void select() {
        // Given
        CallNumber callNumber = createSampleCallNumer();
        int key = callNumberRepository.insert(callNumber);

        // When
        callNumber.setId(key);
        CallNumber inserted = callNumberRepository.select(callNumber);

        // Then
        assertThat(inserted).isEqualTo(callNumber);

        // After
        callNumberRepository.delete(inserted);
    }

    @Test
    public void delete() {
        // Given
        CallNumber callNumber = createSampleCallNumer();
        int key = callNumberRepository.insert(callNumber);

        // When
        callNumber.setId(key);
        callNumberRepository.delete(callNumber);

        // Then
        CallNumber deleted = callNumberRepository.select(callNumber);
        assertThat(deleted).isNull();
    }

    private CallNumber createSampleCallNumer() {
        CallNumber callNumber = new CallNumber();
        callNumber.setId(1);
        callNumber.setOwnerId(1);
        callNumber.setBookId(1);
        return callNumber;
    }

}
