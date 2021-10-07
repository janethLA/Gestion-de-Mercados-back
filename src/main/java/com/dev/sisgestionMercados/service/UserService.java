package com.dev.sisgestionMercados.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.Input.UserInput;
import com.dev.sisgestionMercados.Output.UserOutput;
import com.dev.sisgestionMercados.entity.Role;
import com.dev.sisgestionMercados.entity.Sector;
import com.dev.sisgestionMercados.entity.UserRole;
import com.dev.sisgestionMercados.entity.UserS;
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public UserS save(UserS user) {
	    UserS persistedUser = userRepository.save(user);
	     
	     return persistedUser;
	}
	
	public UserInput save(UserInput user) {
		UserS newUser=new UserS();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setUserName(user.getUsername());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setTelephone(user.getTelephone());
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
	
	public Iterable<UserOutput>  getAllUser(){
		List <UserS> allUsers = userRepository.findAll();
		List <UserOutput> allUsersByOrder = new ArrayList<UserOutput>();
		
		for (UserS found : allUsers ) {
		
			UserOutput newUser = new UserOutput();
			newUser.setIdUser(found.getIdUser());;
			newUser.setName(found.getName());
			newUser.setEmail(found.getEmail());
			newUser.setTelephone(found.getTelephone());
			newUser.setPassword(found.getPassword());
			allUsersByOrder.add(newUser);

		}

		return allUsersByOrder;	
	}
	
    public boolean noExistsUserName(String userName) {
		
		boolean result=true;
		List <UserS> allUser = userRepository.findAll();
		for(UserS a:allUser) {
			if(a.getUserName()!=null){
			if(a.getUserName().equalsIgnoreCase(userName)) {
				result=false;
			}}
		}
		return result;
	}
    
    public UserInput setUser(int id,UserInput user) {
		UserS updateUser=userRepository.findById(id).get();
		if(!user.getEmail().isEmpty()) {
			updateUser.setEmail(user.getEmail());
		}
		if(!user.getName().isEmpty()) {
			updateUser.setName(user.getName());
		}
		if(!user.getPassword().isEmpty()) {
			updateUser.setPassword(encoder.encode(user.getPassword()));
		}
		if(!user.getUsername().isEmpty()) {
			updateUser.setUserName(user.getUsername());
		}
		if(user.getTelephone()!=0) {
			updateUser.setTelephone(user.getTelephone());
		}
		if(user.getIdRole()!=0) {
			UserRole userRole=updateUser.getUserRole().get(0);
			Role role=roleService.findById(user.getIdRole());
			userRole.setRole(role);
			List<UserRole> userRoles=new ArrayList<UserRole>();
			userRoles.add(userRole);
			updateUser.setUserRole(userRoles);
		}
		if(user.getIdSector()!=0) {
			UserRole userRole=updateUser.getUserRole().get(0);
			Sector sector=sectorService.findById(user.getIdSector());
			userRole.setSector(sector);
			List<UserRole> userRoles=new ArrayList<UserRole>();
			userRoles.add(userRole);
			updateUser.setUserRole(userRoles);
		}
		
		userRepository.save(updateUser);
		return user;
    }
    
    public String deleteUser(int id) {
    	try {
    		UserS user=userRepository.findById(id).get();
        	UserRole userRole= userRoleService.findById(user.getUserRole().get(0).getIdUserRole());
        	userRoleService.delete(userRole);
        	userRepository.delete(user);
        	return "Se eliminó correctamente el usuario";
    	}catch (Exception e) {
			return "No se elimino el usuario";
		}
    	
    }
}