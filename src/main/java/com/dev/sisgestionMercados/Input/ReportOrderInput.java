package com.dev.sisgestionMercados.Input;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportOrderInput {

	private int idOrder;
	private LocalDate dateOfOrder;
	private LocalTime hourOfOrder; 
    private int quantityProducts;
	private double totalPrice;
	private String userName;
	private int telephone;
	private String status;
	private double shippingCost;
	private String delivery;
   
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public LocalTime getHourOfOrder() {
		return hourOfOrder;
	}
	public void setHourOfOrder(LocalTime hourOfOrder) {
		this.hourOfOrder = hourOfOrder;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	
}
