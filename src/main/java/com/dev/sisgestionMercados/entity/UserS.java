package com.dev.sisgestionMercados.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "User")
@Table(name = "USER")
public class UserS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	@Column
	private String name;
	@Column(unique = true)
	private String userName;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private LocalDate registrationDate;
	@Column
	private int telephone;
	@Column
	private String whatsappLink;
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
	private List<UserRole> userRole;

	@OneToMany(mappedBy = "userS",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
   	@JsonManagedReference
	private List<OrderAssigned> orderAssigned;
	
	public String getName() {

		return name;
	}

	public int getIdUser() {

		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OrderAssigned> getOrderAssigned() {
		return orderAssigned;
	}

	public void setOrderAssigned(List<OrderAssigned> orderAssigned) {
		this.orderAssigned = orderAssigned;
	}

	public String getWhatsappLink() {
		return whatsappLink;
	}

	public void setWhatsappLink(String whatsappLink) {
		this.whatsappLink = whatsappLink;
	}
	
}
