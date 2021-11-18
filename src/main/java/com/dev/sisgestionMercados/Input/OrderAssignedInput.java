package com.dev.sisgestionMercados.Input;

public class OrderAssignedInput {

	private int idUser;
	private int idOrder;
	private double shippingCost;
	private int idUserCallCenter;
	private int idPayment;
	
	private int idUserOfBuyer;
	private double deliveryCost;
	private double buyerCost;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public int getIdUserCallCenter() {
		return idUserCallCenter;
	}
	public void setIdUserCallCenter(int idUserCallCenter) {
		this.idUserCallCenter = idUserCallCenter;
	}
	public int getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	public int getIdUserOfBuyer() {
		return idUserOfBuyer;
	}
	public void setIdUserOfBuyer(int idUserOfBuyer) {
		this.idUserOfBuyer = idUserOfBuyer;
	}
	public double getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public double getBuyerCost() {
		return buyerCost;
	}
	public void setBuyerCost(double buyerCost) {
		this.buyerCost = buyerCost;
	}
	
}
