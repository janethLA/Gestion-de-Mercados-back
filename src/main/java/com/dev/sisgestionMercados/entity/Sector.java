package com.dev.sisgestionMercados.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Sector")
@Table(name = "SECTOR")
public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSector;
	@Column
	private String sectorName;
	@OneToMany(mappedBy = "sector", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH })
	@JsonManagedReference
	private List<Market> market;
	
	@OneToMany(mappedBy = "sector",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<UserRole> userRole;
	
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public List<Market> getMarket() {
		return market;
	}
	public void setMarket(List<Market> market) {
		this.market = market;
	}
	public List<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	
}
