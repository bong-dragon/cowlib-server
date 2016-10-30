package com.cowlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cowlib.model.User;
import com.cowlib.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthContoller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;

	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	public AuthContoller(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@GetMapping
	public User login(Model model) {

		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);

		if (connection == null) {
			logger.warn("need login");
			return new User();
		}

		String profile = connection.createData().getImageUrl();
		String name = connection.createData().getDisplayName();
		String facebookId = connection.createData().getProviderUserId();

		User user = new User();
		user.setFacebookId(facebookId);
		user.setName(name);
		user.setProfile(profile);

		User select = userRepository.selectByFacebookId(user);
		if (select == null){
			logger.debug("new user insert to db");
			userRepository.insert(user);
			select = userRepository.selectByFacebookId(user);
		}

		return select;
	}


}
