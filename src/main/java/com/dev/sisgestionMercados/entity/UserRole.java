package com.dev.sisgestionMercados.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name = "UserRole")
@Table(name = "USER_ROLE")
public class UserRole {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int idUserRole;	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	private UserS user;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinColumn(name="idRole")
	private Role role;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idSector")
	private Sector sector;
	
	public int getIdUserRole() {
		return idUserRole;
	}
	public void setIdUserRole(int idUserRole) {
		this.idUserRole = idUserRole;
	}
	public UserS getUser() {
		return user;
	}
	public void setUser(UserS user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
}
