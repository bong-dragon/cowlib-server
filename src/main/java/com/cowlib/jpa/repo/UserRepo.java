package com.cowlib.jpa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cowlib.jpa.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
