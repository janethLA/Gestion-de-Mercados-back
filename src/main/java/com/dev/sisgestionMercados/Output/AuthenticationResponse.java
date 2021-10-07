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
	private int identifier;
	private String spendingUnit;
	private String faculty;

	public AuthenticationResponse() {
		
	}
	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles, int id) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.id = id;
	}
	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles, int id, String userName) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.id = id;
		this.userName = userName;
	}

	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles, int id, String userName, int identifier) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.id = id;
		this.userName = userName;
		this.identifier=identifier;
	}
	public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> roles, int id, String userName, int identifier, String spendingUnit , String faculty) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.id = id;
		this.userName = userName;
		this.identifier=identifier;
		this.faculty=faculty;
		this.spendingUnit=spendingUnit;
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

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getSpendingUnit() {
		return spendingUnit;
	}

	public void setSpendingUnit(String spendingUnit) {
		this.spendingUnit = spendingUnit;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
}
