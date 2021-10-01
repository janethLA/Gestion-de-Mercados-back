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

@Entity(name = "Price")
@Table(name = "PRICE")
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrice;
	@Column
	private double price;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "idProduct")
	@JsonBackReference
	private Product product;
	
	public int getIdPrice() {
		return idPrice;
	}
	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
