package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.sisgestionMercados.Output.RoleOutput;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.repository.RoleRepository;



@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;


	@Transactional
	public Role save(Role role) {

	    return roleRepository.save(role);
	}
	
	public Iterable<RoleOutput>  getAllRoles(){
		List <Role> allRoles = roleRepository.findAll();
		List <RoleOutput> allRolesByOrder = new ArrayList<RoleOutput>();
		
		for (Role found : allRoles) {
		
			RoleOutput newRole = new RoleOutput();
			newRole.setIdRole(found.getIdRole());
			newRole.setRoleName(found.getRoleName());
			allRolesByOrder.add(newRole);

		}

		return allRolesByOrder;	
	}
	
	public Role findById(int idRole) {
		return roleRepository.findById(idRole).get();
	}
}
