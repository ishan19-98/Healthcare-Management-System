package com.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int sid;
	public int did;
	public LocalTime time;
	public LocalDate date;
	public boolean bookFlag;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime localTime) {
		this.time = localTime;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	public boolean isBookFlag() {
		return bookFlag;
	}
	public void setBookFlag(boolean bookFlag) {
		this.bookFlag = bookFlag;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	
}
