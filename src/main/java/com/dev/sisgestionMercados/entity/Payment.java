package com.dev.sisgestionMercados.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Payment")
@Table(name = "PAYMENT")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPayment;
	@Column
	private String nroAccount;
	@Column
	private String bankName;
	@Column
	private String nameAccount;
	@Column(columnDefinition = "longblob")
	private byte[] image;
	
	public int getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
}
