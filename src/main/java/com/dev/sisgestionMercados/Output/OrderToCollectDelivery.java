package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;

public class OrderToCollectDelivery {

	private int idOrder;
	private int idDelivery;
	private String deliveryName;
	private double shippingCost;
	private double totalPrice;
	private LocalDate dateOfOrderAssigned;
	private String statusOfOrder;
	private String substateOfOrder;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDate getDateOfOrderAssigned() {
		return dateOfOrderAssigned;
	}
	public void setDateOfOrderAssigned(LocalDate dateOfOrderAssigned) {
		this.dateOfOrderAssigned = dateOfOrderAssigned;
	}
	public String getStatusOfOrder() {
		return statusOfOrder;
	}
	public void setStatusOfOrder(String statusOfOrder) {
		this.statusOfOrder = statusOfOrder;
	}
	public String getSubstateOfOrder() {
		return substateOfOrder;
	}
	public void setSubstateOfOrder(String substateOfOrder) {
		this.substateOfOrder = substateOfOrder;
	}
	public int getIdDelivery() {
		return idDelivery;
	}
	public void setIdDelivery(int idDelivery) {
		this.idDelivery = idDelivery;
	}
	
}
