package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;

import com.dev.sisgestionMercados.entity.OrderP;


public class OrderAssignedOutput {
	
	private int idOrderAssigned;
	private String status;
	private LocalDate date;
	private LocalTime hour;
	private String userName;
	private int userTelephone;
	private String userEmail;
	private String warehouseNameOfOrder;
	private String warehouseAddressOfOrder;
	private String warehouseSectorOfOrder;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(int userTelephone) {
		this.userTelephone = userTelephone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
    
	
}
