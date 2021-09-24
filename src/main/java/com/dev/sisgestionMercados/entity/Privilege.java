package com.dev.sisgestionMercados.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

//@Data
@Entity(name = "Privilege" )
@Table(name = "PRIVILEGE")
public class Privilege {
 
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int idPrivilege;
	@Column
	private String privilege;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idRole")
	@JsonBackReference
	private Role roles;

	public int getIdPrivilege() {
		return idPrivilege;
	}
	public void setIdPrivilege(int idPrivilege) {
		this.idPrivilege = idPrivilege;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public Role getRoles() {
		return roles;
	}
	public void setRoles(Role roles) {
		this.roles = roles;
	}

}
