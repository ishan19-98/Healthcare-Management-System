package com.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer billId;
	public Integer aid;
	public String patientName;
	public String doctorName;
	public Float billamount;
	
	
	
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Float getBillamount() {
		return billamount;
	}
	public void setBillamount(Float billamount) {
		this.billamount = billamount;
	}
	
	

}
