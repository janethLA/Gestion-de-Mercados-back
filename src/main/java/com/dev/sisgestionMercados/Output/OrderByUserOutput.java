package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.dev.sisgestionMercados.entity.OrderDetail;

public class OrderByUserOutput {
 
	private int idOrder;
	private int quantityProducts;
	private double totalPrice;
	private String status;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private double shippingCost;
	private String warehouseName;
	private String sectorName;
	private List<OrderDetail> orderDetail;
	
	private String adminName;
	private int adminTelephone;
	private String adminEmail;
	private String adminWhatsappLink;
	
	private String nroAccount;
	private String bankName;
	private String nameAccount;
	private byte[] qr;
	
	

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

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
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

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminWhatsappLink() {
		return adminWhatsappLink;
	}

	public void setAdminWhatsappLink(String adminWhatsappLink) {
		this.adminWhatsappLink = adminWhatsappLink;
	}

	public String getNroAccount() {
		return nroAccount;
	}

	public void setNroAccount(String nroAccount) {
		this.nroAccount = nroAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

	public byte[] getQr() {
		return qr;
	}

	public void setQr(byte[] qr) {
		this.qr = qr;
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
	
}
