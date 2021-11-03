package com.dev.sisgestionMercados.Output;

import java.time.LocalDate;
import java.time.LocalTime;

public class AllOrderAssignedOutput {

	private int idOrderAssigned;
	private String status;
	private LocalDate date;
	private LocalTime hour;
	private int idOrder;
	private String commentary;
	
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
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	
}
