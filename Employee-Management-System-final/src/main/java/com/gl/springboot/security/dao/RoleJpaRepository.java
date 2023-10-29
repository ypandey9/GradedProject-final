package com.gl.springboot.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.springboot.security.model.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Integer>{

}
