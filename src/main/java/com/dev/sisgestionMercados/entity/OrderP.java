package com.dev.sisgestionMercados.entity;

import java.time.LocalDate;
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

@Entity(name = "OrderP")
@Table(name = "ORDER_P")
public class OrderP{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;
	@Column
	private int quantityProducts;
	@Column
	private double totalPrice;
	@Column
	private String status;
	@Column
	private LocalDate orderDate;
	
	@OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference
	private List<OrderDetail> orderDetail;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idFinalUser")
	@JsonBackReference
	private FinalUser finalUser;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getQuantityProducts() {
		return quantityProducts;
	}
	public void setQuantityProducts(int quantityProducts) {
		this.quantityProducts = quantityProducts;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public FinalUser getFinalUser() {
		return finalUser;
	}
	public void setFinalUser(FinalUser finalUser) {
		this.finalUser = finalUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
