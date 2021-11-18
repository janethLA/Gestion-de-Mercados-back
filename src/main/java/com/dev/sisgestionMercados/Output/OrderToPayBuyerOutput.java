package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;

public class OrderToPayBuyerOutput {

	private int idOrder;
	private int idBuyer;
	private String buyerName;
	private double buyerCost;
	private LocalDate dateOfOrderAssigned;
	private String status;
	private String paymentStatusToBuyer;
	
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdBuyer() {
		return idBuyer;
	}
	public void setIdBuyer(int idBuyer) {
		this.idBuyer = idBuyer;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public double getBuyerCost() {
		return buyerCost;
	}
	public void setBuyerCost(double buyerCost) {
		this.buyerCost = buyerCost;
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
	public String getPaymentStatusToBuyer() {
		return paymentStatusToBuyer;
	}
	public void setPaymentStatusToBuyer(String paymentStatusToBuyer) {
		this.paymentStatusToBuyer = paymentStatusToBuyer;
	}
	
}
