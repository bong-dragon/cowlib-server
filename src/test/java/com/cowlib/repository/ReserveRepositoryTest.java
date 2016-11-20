package com.cowlib.repository;

import com.Application;
import com.cowlib.code.ReserveStatus;
import com.cowlib.model.Reserve;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("/application-local.properties")
@Sql({"classpath:drop_tables.sql", "classpath:create_tables.sql", "classpath:insert_test_data.sql"})
public class ReserveRepositoryTest {

    @Autowired
    private ReserveRepository reserveRepository;

    @Test
    public void insert() {
        // Given
        Reserve reserve = createSampleReserve();

        // When
        reserveRepository.insert(reserve);

        // Then
        Reserve inserted = reserveRepository.select(reserve);
        assertThat(inserted.getId()).isEqualTo(reserve.getId());

        // After
        reserveRepository.delete(inserted);
    }

    @Test
    public void select() {
        // Given
        Reserve reserve = createSampleReserve();
        reserveRepository.insert(reserve);

        // When
        Reserve inserted = reserveRepository.select(reserve);

        // Then
        assertThat(inserted).isEqualTo(reserve);

        // After
        reserveRepository.delete(reserve);
    }

    @Test
    public void delete() {
        // Given
        Reserve reserve = createSampleReserve();
        reserveRepository.insert(reserve);

        // When
        reserveRepository.delete(reserve);

        // Then
        Reserve deleted = reserveRepository.select(reserve);
        assertThat(deleted).isNull();
    }

    @Test
    public void update() {
        // Given
        Reserve borrow = createSampleReserve();
        reserveRepository.insert(borrow);

        // When
        borrow.setStatus("CANCEL");
        reserveRepository.update(borrow);

        // Then
        Reserve updated = reserveRepository.select(borrow);
        assertThat(updated.getStatus()).isEqualTo("CANCEL");

        // After
        reserveRepository.delete(updated);
    }

    @Test
    public void selectByCallNumberIdAndStatus() {
        // Given
        Reserve query = new Reserve();
        query.setCallNumberId(1);
        query.setStatus(ReserveStatus.예약함.getCode());

        // When
        List<Reserve> reserves = reserveRepository.selectByCallNumberIdAndStatus(query);

        // Then
        assertThat(reserves.size()).isEqualTo(1);
    }

    private Reserve createSampleReserve() {
        Reserve reserve = new Reserve();
        reserve.setReserverId(1);
        reserve.setCallNumberId(1);
        reserve.setStatus(ReserveStatus.예약함.getCode());
        return reserve;
    }

}
