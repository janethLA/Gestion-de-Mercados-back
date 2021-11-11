package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.dev.sisgestionMercados.entity.OrderDetail;


public class OrderOutput {

	private int idOrder;
	private int quantityProducts;
	private double totalPrice;
	private String status;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private double shippingCost;
	
	private String warehouseName;
	private String sectorName;
	
	private String finalUserName;
	private int finalUserTelephone;
	private String finalUserWhatsappLink;
	
	private String deliveryName;
	private int deliveryTelephone;
	private String deliveryEmail;
	private String deliveryWhatsappLink;
	
    private List<OrderDetail> orderDetail;
    
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getFinalUserName() {
		return finalUserName;
	}
	public void setFinalUserName(String finalUserName) {
		this.finalUserName = finalUserName;
	}
	public int getFinalUserTelephone() {
		return finalUserTelephone;
	}
	public void setFinalUserTelephone(int finalUserTelephone) {
		this.finalUserTelephone = finalUserTelephone;
	}
	public String getFinalUserWhatsappLink() {
		return finalUserWhatsappLink;
	}
	public void setFinalUserWhatsappLink(String finalUserWhatsappLink) {
		this.finalUserWhatsappLink = finalUserWhatsappLink;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public int getDeliveryTelephone() {
		return deliveryTelephone;
	}
	public void setDeliveryTelephone(int deliveryTelephone) {
		this.deliveryTelephone = deliveryTelephone;
	}
	public String getDeliveryEmail() {
		return deliveryEmail;
	}
	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}
	public String getDeliveryWhatsappLink() {
		return deliveryWhatsappLink;
	}
	public void setDeliveryWhatsappLink(String deliveryWhatsappLink) {
		this.deliveryWhatsappLink = deliveryWhatsappLink;
	}
    
}
