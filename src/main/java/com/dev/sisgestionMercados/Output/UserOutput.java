package com.dev.sisgestionMercados.Output;

import javax.persistence.Column;

public class UserOutput {

	private int idUser;
	private String name;
	private String email;
	private int telephonne;
	private String password;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelephonne() {
		return telephonne;
	}
	public void setTelephonne(int telephonne) {
		this.telephonne = telephonne;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}