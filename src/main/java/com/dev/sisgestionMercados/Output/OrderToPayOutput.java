package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderToPayOutput {
	
	private int idOrder;
	private String delivery;
	private double shippingCost;
	private LocalDate dateOfOrderAssigned;
	private String status;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public LocalDate getDateOfOrderAssigned() {
		return dateOfOrderAssigned;
	}
	public void setDateOfOrderAssigned(LocalDate dateOfOrderAssigned) {
		this.dateOfOrderAssigned = dateOfOrderAssigned;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
