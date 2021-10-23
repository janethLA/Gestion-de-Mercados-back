package com.dev.sisgestionMercados.Output;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class FinalUserOutput {

	private String jwt;
	Collection<? extends GrantedAuthority> roles;
	private long idFinalUser;
	String finalUserName;
	String userName;

	
   public FinalUserOutput () {
		
	}
	public FinalUserOutput(String jwt, Collection<? extends GrantedAuthority> roles, long idFinalUser, String userName,String finalUserName) {
		super();
		this.jwt = jwt;
		this.roles = roles;
		this.idFinalUser = idFinalUser;
		this.userName = userName;
		this.finalUserName=finalUserName;
	}
	
	public long getIdFinalUser() {
		return idFinalUser;
	}
	public void setIdFinalUser(long idFinalUser) {
		this.idFinalUser = idFinalUser;
	}
	public String getFinalUserName() {
		return finalUserName;
	}
	public void setFinalUserName(String finalUserName) {
		this.finalUserName = finalUserName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
	
}
