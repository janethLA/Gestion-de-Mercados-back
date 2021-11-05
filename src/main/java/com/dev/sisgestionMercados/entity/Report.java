package com.dev.sisgestionMercados.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Report")
@Table(name = "REPORT")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReport;
	@Column
	private String reportName;
	@Column(columnDefinition = "longblob")
	private byte[] report;
	
	public long getIdReport() {
		return idReport;
	}
	public void setIdReport(long idReport) {
		this.idReport = idReport;
	}
	public byte[] getReport() {
		return report;
	}
	public void setReport(byte[] report) {
		this.report = report;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
    
}
