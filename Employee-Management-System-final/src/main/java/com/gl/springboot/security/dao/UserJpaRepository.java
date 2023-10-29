package com.gl.springboot.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.springboot.security.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer>{

	Optional<User> findByName(String username);
}
