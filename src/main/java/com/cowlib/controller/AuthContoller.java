package com.cowlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cowlib.model.User;
import com.cowlib.repository.UserRepository;

@RestController
@RequestMapping("/v1/auth")
public class AuthContoller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User loginFromNode(User user) {
        User select = userRepository.selectByFacebookId(user);
        if (select == null) {
            logger.debug("new user insert to db");
            userRepository.insert(user);
            select = userRepository.selectByFacebookId(user);
        }
        return select;
    }

}
