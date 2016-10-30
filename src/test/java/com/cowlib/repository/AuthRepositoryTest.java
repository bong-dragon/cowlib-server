package com.cowlib.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.Application;
import com.cowlib.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("/application-local.properties")
public class AuthRepositoryTest {

	@Autowired
	private AuthRepository authRepository;

	@Test
	public void test() {
		User user = authRepository.getUser();
		System.out.println(user);
	}
}
