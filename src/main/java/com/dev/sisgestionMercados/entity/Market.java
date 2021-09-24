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

@Entity(name = "Market")
@Table(name = "MARKET")
public class Market {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMarket;
	@Column
	private String marketName;
	@Column
	private String address;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "idSector")
	@JsonBackReference
	private Sector sector;
	
	public int getIdMarket() {
		return idMarket;
	}
	public void setIdMarket(int idMarket) {
		this.idMarket = idMarket;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
}
