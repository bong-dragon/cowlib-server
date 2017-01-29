package com.cowlib.jpa.repo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.cowlib.jpa.model.User;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application-local.properties")
public class UserRepoTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepo repo;

	@Test
	public void test() {
		User user = new User();

		user.setProfile("profileurl");
		user.setName("name");
		user.setFacebookId("facebook");

		User save = repo.save(user);

		assertNotNull(save);
		logger.info("{}",save);
	}

}