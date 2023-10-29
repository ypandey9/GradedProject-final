package com.gl.springboot.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.springboot.security.dao.RoleJpaRepository;
import com.gl.springboot.security.model.Role;
import com.gl.springboot.security.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleJpaRepository roleRepository;

	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	public RoleServiceImpl(RoleJpaRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
	
	

}
