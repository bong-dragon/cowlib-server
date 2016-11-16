package com.cowlib.repository;

import com.Application;
import com.cowlib.model.Wait;
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
public class WaitRepositoryTest {

    @Autowired
    private WaitRepository waitRepository;

    @Test
    public void insert() {
        // Given
        Wait wait = createSampleWait();

        // When
        waitRepository.insert(wait);

        // Then
        Wait inserted = waitRepository.select(wait);
        assertThat(inserted).isEqualTo(wait);

        // After
        waitRepository.delete(inserted);
    }

    @Test
    public void select() {
        // Given
        Wait wait = createSampleWait();
        waitRepository.insert(wait);

        // When
        Wait inserted = waitRepository.select(wait);

        // Then
        assertThat(inserted).isEqualTo(wait);

        // After
        waitRepository.delete(wait);
    }

    @Test
    public void delete() {
        // Given
        Wait wait = createSampleWait();
        waitRepository.insert(wait);

        // When
        waitRepository.delete(wait);

        // Then
        Wait deleted = waitRepository.select(wait);
        assertThat(deleted).isNull();
    }

    @Test
    public void update() {
        // Given
        Wait borrow = createSampleWait();
        waitRepository.insert(borrow);

        // When
        borrow.setStatus("CANCEL");
        waitRepository.update(borrow);

        // Then
        Wait updated = waitRepository.select(borrow);
        assertThat(updated.getStatus()).isEqualTo("CANCEL");

        // After
        waitRepository.delete(updated);
    }

    private Wait createSampleWait() {
        Wait wait = new Wait();
        wait.setWaiterId(1);
        wait.setCallNumberId(1);
        wait.setStatus("WAIT");
        return wait;
    }

}
