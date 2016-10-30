package com.cowlib;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthContoller {

	@RequestMapping("/greeting")
	public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		User user = new User();
		user.setId(123);
		return user;
	}
}
