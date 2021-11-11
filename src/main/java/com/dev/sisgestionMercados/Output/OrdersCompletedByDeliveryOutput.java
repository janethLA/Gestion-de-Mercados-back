package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;

public class OrdersCompletedByDeliveryOutput {

	private int idOrder;
	private String delivery;
	private double shippingCost;
	private LocalDate dateOfOrderAssigned;
	private LocalDate paymentDate;
	private long receiptNumber;
	private String statusOfOrder;
	private String statusOfOrderAssigned;
	
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
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getStatusOfOrder() {
		return statusOfOrder;
	}
	public void setStatusOfOrder(String statusOfOrder) {
		this.statusOfOrder = statusOfOrder;
	}
	public String getStatusOfOrderAssigned() {
		return statusOfOrderAssigned;
	}
	public void setStatusOfOrderAssigned(String statusOfOrderAssigned) {
		this.statusOfOrderAssigned = statusOfOrderAssigned;
	}
	public long getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	
}
