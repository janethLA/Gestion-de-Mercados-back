package com.dev.sisgestionMercados.Output;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthenticationResponse {

	private String jwt;
	Collection<? extends GrantedAuthority> roles;
	private int id;
	String userName;
	String name;

	public AuthenticationResponse() {
		
	}
	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles, int id) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.id = id;
	}
	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles, int id, String userName,String name) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.id = id;
		this.userName = userName;
		this.name=name;
	}

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles) {
		super();
		this.jwt = jwt;
		this.roles = roles;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
