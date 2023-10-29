package com.gl.springboot.security.service;

import java.util.Optional;

import com.gl.springboot.security.model.User;

//UserService.java
public interface UserService {
	Optional<User> findByUserName(String username);
	User save(User user);
}
