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
import javax.persistence.OneToOne;
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
	@Column(length = 1000)
	private String commentary;
	@Column
	private boolean reassigned;
	@Column
	private int idUserCallCenter;
	@Column
	private LocalDate paymentDate;
	@Column
	private long receiptNumber;
	
	@Column
	private int idUserOfBuyer;
	@Column
	private String paymentStatusToBuyer;
	@Column 
	private LocalDate paymentDateOfBuyer;
	@Column
	private long receiptNumberOfBuyer;
	@Column
	private long receiptNumberOfCollect;
	@Column
	private double deliveryCost;
	@Column
	private double buyerCost;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference
	private UserS userS;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idOrder")
    @JsonBackReference(value="order-assigned")
	private OrderP orderP;

	@JoinColumn(name = "idPayment" )
	@OneToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH} )
	private Payment payment; 
	
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

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public boolean isReassigned() {
		return reassigned;
	}

	public void setReassigned(boolean reassigned) {
		this.reassigned = reassigned;
	}

	public int getIdUserCallCenter() {
		return idUserCallCenter;
	}

	public void setIdUserCallCenter(int idUserCallCenter) {
		this.idUserCallCenter = idUserCallCenter;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public long getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public int getIdUserOfBuyer() {
		return idUserOfBuyer;
	}

	public void setIdUserOfBuyer(int idUserOfBuyer) {
		this.idUserOfBuyer = idUserOfBuyer;
	}

	public LocalDate getPaymentDateOfBuyer() {
		return paymentDateOfBuyer;
	}

	public void setPaymentDateOfBuyer(LocalDate paymentDateOfBuyer) {
		this.paymentDateOfBuyer = paymentDateOfBuyer;
	}

	public long getReceiptNumberOfBuyer() {
		return receiptNumberOfBuyer;
	}

	public void setReceiptNumberOfBuyer(long receiptNumberOfBuyer) {
		this.receiptNumberOfBuyer = receiptNumberOfBuyer;
	}

	public long getReceiptNumberOfCollect() {
		return receiptNumberOfCollect;
	}

	public void setReceiptNumberOfCollect(long receiptNumberOfCollect) {
		this.receiptNumberOfCollect = receiptNumberOfCollect;
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

	public String getPaymentStatusToBuyer() {
		return paymentStatusToBuyer;
	}

	public void setPaymentStatusToBuyer(String paymentStatusToBuyer) {
		this.paymentStatusToBuyer = paymentStatusToBuyer;
	}
	
}
