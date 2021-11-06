package com.dev.sisgestionMercados.Input;

public class OrderAssignedInput {

	private int idUser;
	private int idOrder;
	private double shippingCost;
	private int idUserCallCenter;
	private int idPayment;
	
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
	
}
