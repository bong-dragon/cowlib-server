package com.cowlib;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthContoller {


	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	public AuthContoller(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping("/greeting")
	@ResponseBody
	public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		User user = new User();
		user.setId(123);
		return user;
	}

	@GetMapping
	@ResponseBody
	public org.springframework.social.facebook.api.User login(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//			return "redirect:/connect/facebook";
			return null;
		}
		return facebook.userOperations().getUserProfile();
	}


}
