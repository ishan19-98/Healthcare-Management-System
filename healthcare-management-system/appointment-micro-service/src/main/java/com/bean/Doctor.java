package com.bean;

import java.sql.Time;
import java.time.LocalTime;

public class Doctor {

	public int did;
	public String dname;
	public int age;
	public long phoneno;
	public String[] slotAvailibility; 
	
	public String[] getSlotAvailibility() {
		return slotAvailibility;
	}
	public void setSlotAvailibility(String[] slotAvailibility) {
		this.slotAvailibility = slotAvailibility;
	}
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
	
	
}