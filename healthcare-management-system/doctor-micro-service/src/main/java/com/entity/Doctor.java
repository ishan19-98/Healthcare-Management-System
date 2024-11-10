package com.entity;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor {

	@Id
	public int did;
	public String dname;
	public int age;
	public long phoneno;
	public String[] slotAvailibility; 
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public String[] getSlotAvailibility() {
		return slotAvailibility;
	}
	public void setSlotAvailibility(String[] slotAvailibility) {
		this.slotAvailibility = slotAvailibility;
	}
	
	
}