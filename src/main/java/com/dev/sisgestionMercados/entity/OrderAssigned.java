package com.dev.sisgestionMercados.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "OrderAssigned")
@Table(name = "ORDER_ASSIGNED")
public class OrderAssigned {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderAssigned;
	@Column
	private String status;
	@Column
	private LocalDate date;
	@Column
	private LocalTime hour;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference
	private UserS userS;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idOrder")
    @JsonBackReference
	private OrderP orderP;

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

	public UserS getUserS() {
		return userS;
	}

	public void setUserS(UserS userS) {
		this.userS = userS;
	}

	public OrderP getOrderP() {
		return orderP;
	}

	public void setOrderP(OrderP orderP) {
		this.orderP = orderP;
	}
	
	
}
