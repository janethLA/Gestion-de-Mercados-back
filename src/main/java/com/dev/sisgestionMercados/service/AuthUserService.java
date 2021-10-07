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

import com.dev.sisgestionMercados.entity.Privilege;
import com.dev.sisgestionMercados.entity.UserS;
import com.dev.sisgestionMercados.repository.UserRepository;


@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserS us= userRepository.findByUserName(username);
		int i=0;
		List <GrantedAuthority> roles=new ArrayList<>();
		List <Privilege> rol=us.getUserRole().get(0).getRole().getPrivileges();
		for(Privilege r:rol) {
			roles.add(new SimpleGrantedAuthority(r.getPrivilege()));
			System.out.println("-------------------> "+us.getUserRole().get(0).getRole().getPrivileges().get(i).getPrivilege());
			i++;
		}
		//roles.add(new SimpleGrantedAuthority(us.getUserRole().get(0).getRole().getPrivileges());
		
		UserDetails userDetails=new User(us.getUserName(),us.getPassword(),roles);
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
	
	
	
}
