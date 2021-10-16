package com.dev.sisgestionMercados.Input;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

public class OrderInput {
	
	private int quantityProducts;
	private double totalPrice;
    private List<OrderDetailInput> orderDetails;
    
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
	public List<OrderDetailInput> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailInput> orderDetails) {
		this.orderDetails = orderDetails;
	}
    
    
    
}
