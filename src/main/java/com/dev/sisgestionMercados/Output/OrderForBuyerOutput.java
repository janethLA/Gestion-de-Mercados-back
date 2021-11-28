package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.dev.sisgestionMercados.entity.OrderDetail;
import com.dev.sisgestionMercados.entity.OrderP;

public class OrderForBuyerOutput {

	private int idOrder;
	private int quantityProducts;
	private double totalPrice;
	private String statusOrder;
	private LocalDate orderAssignedDate;
	private LocalTime orderAssignedTime;
	private double shippingCost;
	private String warehouseName;
	private String sectorName;
	private List<OrderDetail> orderDetail;
	
	private String deliveryName;
	private int deliveryTelephone;
	private String deliveryWhatsappLink;
	
	private String adminName;
	private int adminTelephone;
	private String adminWhatsappLink;

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

	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}

	public LocalDate getOrderAssignedDate() {
		return orderAssignedDate;
	}

	public void setOrderAssignedDate(LocalDate orderAssignedDate) {
		this.orderAssignedDate = orderAssignedDate;
	}

	public LocalTime getOrderAssignedTime() {
		return orderAssignedTime;
	}

	public void setOrderAssignedTime(LocalTime orderAssignedTime) {
		this.orderAssignedTime = orderAssignedTime;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
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

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
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

	public String getDeliveryWhatsappLink() {
		return deliveryWhatsappLink;
	}

	public void setDeliveryWhatsappLink(String deliveryWhatsappLink) {
		this.deliveryWhatsappLink = deliveryWhatsappLink;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public int getAdminTelephone() {
		return adminTelephone;
	}

	public void setAdminTelephone(int adminTelephone) {
		this.adminTelephone = adminTelephone;
	}

	public String getAdminWhatsappLink() {
		return adminWhatsappLink;
	}

	public void setAdminWhatsappLink(String adminWhatsappLink) {
		this.adminWhatsappLink = adminWhatsappLink;
	}
	
}
