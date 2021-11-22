package com.dev.sisgestionMercados.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.sisgestionMercados.entity.FinalUser;
import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.FinalUserRepository;
import com.dev.sisgestionMercados.repository.UserRepository;


@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FinalUserRepository finalUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		int resultado =getUserType(username);
		UserDetails userDetails=null;
		if (resultado==1) {
			UserS us= userRepository.findByUserName(username);
			int i=0;
			List <GrantedAuthority> roles=new ArrayList<>();
			List <Privilege> rol=us.getUserRole().get(0).getRole().getPrivileges();
			for(Privilege r:rol) {
				roles.add(new SimpleGrantedAuthority(r.getPrivilege()));
				i++;
			}
			
		    userDetails=new User(us.getUserName(),us.getPassword(),roles);
		}
		if (resultado==2) {
			FinalUser us=finalUserRepository.findByUserName(username);
			int i=0;
			List <GrantedAuthority> roles=new ArrayList<>();
			 roles.add(new SimpleGrantedAuthority(us.getPrivilege().getPrivilege()));
			 System.out.println("-------------------> "+us.getPrivilege().getPrivilege());
		    userDetails=new User(us.getUserName(),us.getPassword(),roles);
		}
		
		return userDetails;
	}
	
	
	public String getNameUser(String name) {
		return userRepository.findByUserName(name).getUserName();
	}
	
	public int getIdUser(String name) {
		return userRepository.findByUserName(name).getIdUser();
	}
	public String getName(String name) {
		return userRepository.findByUserName(name).getName();
	}
	public boolean getActiveUser(String name) {
		return userRepository.findByUserName(name).isActive();
	}
	
	public int getUserType(String username) {
		int resultado=0;
		List<UserS> allUsers=userRepository.findAll();
		List<FinalUser> finalUser=finalUserRepository.findAll();
		for(UserS u: allUsers) {
			if(u.getUserName().equals(username)) {
				resultado =1;
			}
		}
		for(FinalUser u: finalUser) {
			if(u.getUserName().equals(username)) {
				resultado =2;
			}
		}
		
		return resultado;
	}
	
	public String getFinalNameUser(String name) {
		return finalUserRepository.findByUserName(name).getUserName();
	}
	
	public long getIdFinalUser(String name) {
		return finalUserRepository.findByUserName(name).getIdFinalUser();
	}
	public String getFinalName(String name) {
		return finalUserRepository.findByUserName(name).getFinalUserName();
	}
	public String getTelephone(String name) {
		return Long.toString(finalUserRepository.findByUserName(name).getTelephone());
	}
	public String getPassword(String name) {
		return finalUserRepository.findByUserName(name).getPassword();
	}
	public boolean getActiveFinalUser(String name) {
		return finalUserRepository.findByUserName(name).isActive();
	}
	
}
