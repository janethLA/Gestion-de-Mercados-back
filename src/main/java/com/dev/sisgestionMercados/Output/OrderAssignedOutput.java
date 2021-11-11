package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;

import com.dev.sisgestionMercados.entity.OrderP;


public class OrderAssignedOutput {
	
	private int idOrderAssigned;
	private String status;
	private LocalDate date;
	private LocalTime hour;
	private String warehouseNameOfOrder;
	private String warehouseAddressOfOrder;
	private String warehouseSectorOfOrder;
	
	private String finalUserName;
	private int finalUserTelephone;
	private String finalUserWhatsappLink;
	
	private String adminName;
	private int adminTelephone;
	private String adminEmail;
	private String adminWhatsappLink;
	
	private OrderP order;
	
	public int getIdOrderAssigned() {
		return idOrderAssigned;
	}
	public void setIdOrderAssigned(int idOrderAssigned) {
		this.idOrderAssigned = idOrderAssigned;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHour() {
		return hour;
	}
	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
	public OrderP getOrder() {
		return order;
	}
	public void setOrder(OrderP order) {
		this.order = order;
	}
	public String getWarehouseNameOfOrder() {
		return warehouseNameOfOrder;
	}
	public void setWarehouseNameOfOrder(String warehouseNameOfOrder) {
		this.warehouseNameOfOrder = warehouseNameOfOrder;
	}
	public String getWarehouseAddressOfOrder() {
		return warehouseAddressOfOrder;
	}
	public void setWarehouseAddressOfOrder(String warehouseAddressOfOrder) {
		this.warehouseAddressOfOrder = warehouseAddressOfOrder;
	}
	public String getWarehouseSectorOfOrder() {
		return warehouseSectorOfOrder;
	}
	public void setWarehouseSectorOfOrder(String warehouseSectorOfOrder) {
		this.warehouseSectorOfOrder = warehouseSectorOfOrder;
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
	
}
