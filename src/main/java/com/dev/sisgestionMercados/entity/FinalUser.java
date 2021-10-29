
package com.dev.sisgestionMercados.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "FinalUser")
@Table(name = "FINAL_USER")
public class FinalUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFinalUser;
	@Column
	private String finalUserName;
	@Column
	private String userName;
	@Column
	private int telephone;
	@Column
	private String code;
	@Column
	private String email;
	@Column
	private boolean active;
	@OneToMany(mappedBy = "finalUser",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference
	private List<OrderP> order;
	@JoinColumn(name = "idPrivilege" )
	@OneToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH} )
	private Privilege privilege;
	
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
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public List<OrderP> getOrder() {
		return order;
	}
	public void setOrder(List<OrderP> order) {
		this.order = order;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

