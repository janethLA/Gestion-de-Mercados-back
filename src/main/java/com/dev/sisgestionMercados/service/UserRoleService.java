package com.dev.sisgestionMercados.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.entity.UserRole;
import com.dev.sisgestionMercados.repository.UserRoleRepository;


@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
    @Autowired
	private ModelMapper modelMapper;

	
	public UserRole save(UserRole userRole) {
	    UserRole persistedUserRole = userRoleRepository.save(userRole);
	     
	     return persistedUserRole;
	}
	
	public  UserRole findById(int id) {
		return userRoleRepository.findById(id).get();
	}
	
	public void delete(UserRole userRole) {
		userRoleRepository.delete(userRole);
	}
	
}
