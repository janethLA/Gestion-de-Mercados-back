package com.dev.sisgestionMercados.entity;

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

@Entity(name = "Setting")
@Table(name = "SETTING")
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSetting;
	@Column
	private String nameForReport;
	@Column
	private String telephoneForReport;
	@Column
	private String emailForReport;
	@Column
	private String addresForReport;
	@Column
	private String googleKey;
	@Column
	private int searchDistance;
	
	public int getIdSetting() {
		return idSetting;
	}
	public void setIdSetting(int idSetting) {
		this.idSetting = idSetting;
	}
	public String getNameForReport() {
		return nameForReport;
	}
	public void setNameForReport(String nameForReport) {
		this.nameForReport = nameForReport;
	}
	public String getTelephoneForReport() {
		return telephoneForReport;
	}
	public void setTelephoneForReport(String telephoneForReport) {
		this.telephoneForReport = telephoneForReport;
	}
	public String getEmailForReport() {
		return emailForReport;
	}
	public void setEmailForReport(String emailForReport) {
		this.emailForReport = emailForReport;
	}
	public String getAddresForReport() {
		return addresForReport;
	}
	public void setAddresForReport(String addresForReport) {
		this.addresForReport = addresForReport;
	}
	public String getGoogleKey() {
		return googleKey;
	}
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}
	public int getSearchDistance() {
		return searchDistance;
	}
	public void setSearchDistance(int searchDistance) {
		this.searchDistance = searchDistance;
	}
	
	
}

