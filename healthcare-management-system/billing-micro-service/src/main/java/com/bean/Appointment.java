package com.bean;

import java.sql.Time;

public class Appointment {

	public int aid;
	public String pid;
	public String patientName;
	public String did;
	public String doctorName;
	public float amount;
	public Time timeslot;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}

	public float getAmount() {
		return amount;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public Time getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(Time timeslot) {
		this.timeslot = timeslot;
	}
	public void setAmount(float amount) {
		this.amount = amount;
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
}