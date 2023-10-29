package com.gl.springboot.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.springboot.security.dao.UserJpaRepository;
import com.gl.springboot.security.model.User;
import com.gl.springboot.security.service.UserService;

//UserServiceImpl.java
@Service
public class UserServiceImpl implements UserService {
 @Autowired
 private UserJpaRepository userRepository;
 
 @Autowired
 private BCryptPasswordEncoder passwordEncoder;
/*
 @Override
 public User save(User user) {
     return userRepository.save(user);
 }
 */
 
 @Override
 public User save(User user) {
     // Hash the password before saving
     String hashedPassword = passwordEncoder.encode(user.getPassword());
     user.setPassword(hashedPassword);

     return userRepository.save(user);
 }

 @Override
 public Optional<User> findByUserName(String username) {
     return userRepository.findByName(username);
 }
 
}

