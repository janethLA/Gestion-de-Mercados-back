package com.dev.sisgestionMercados.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.UserInput;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.entity.Sector;
import com.dev.sisgestionMercados.entity.UserRole;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.RoleRepository;
import com.dev.sisgestionMercados.repository.SectorRepository;
import com.dev.sisgestionMercados.repository.UserRepository;
import com.dev.sisgestionMercados.repository.UserRoleRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private ModelMapper modelMapper;
	
	public UserInput save(UserInput user) {
		UserS newUser=new UserS();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setRegistrationDate(LocalDate.now());
	    userRepository.save(newUser);
	    putUserRole(user.getIdRole(),user.getIdSector(),newUser);
	    return user;
	}
	
	private void putUserRole(int idRole,int idSector, UserS user) {
		UserRole userRole=new UserRole();
		Role role= roleService.findById(idRole);
		Sector sector=sectorService.findById(idSector);
		userRole.setRole(role);
		userRole.setSector(sector);
		userRole.setUser(user);
		userRoleService.save(userRole);	
	}
}
