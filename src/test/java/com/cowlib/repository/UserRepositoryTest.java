package com.cowlib.repository;

import com.Application;
import com.cowlib.model.User;
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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insert() {
        // Given
        User user = createSampleUser();

        // When
        userRepository.insert(user);

        // Then
        User inserted = userRepository.select(user);
        assertThat(inserted).isEqualTo(user);

        // After
        userRepository.delete(inserted);
    }


    @Test
    public void selectById() {
        User user = userRepository.selectById(1);

        assertThat(user.getId()).isEqualTo(1);
    }

    private User createSampleUser() {
        User user = new User();
        user.setFacebookId("facebookId");
        user.setName("name");
        user.setProfile("profile");
        return user;
    }

}
