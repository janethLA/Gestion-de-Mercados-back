package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderToPayOutput {
	
	private int idOrder;
	private int idDelivery;
	private String delivery;
	private double deliveryCost;
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
	public double getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
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
	public int getIdDelivery() {
		return idDelivery;
	}
	public void setIdDelivery(int idDelivery) {
		this.idDelivery = idDelivery;
	}
	
	
	
}
