package com.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DoctorDTO {

	@NotNull
	@Positive(message = "Id should be positive")	
	public Integer did;
	
	@NotBlank(message = "Name is a mandatory field.")
	public String dname;
	
	@Positive(message = "Age should be greater than 0")
	public Integer age;
	public Long phoneno;
	public String[] slotAvailibility; 
	
	// Flag to indicate if slots creation is pending
    private Boolean slotsPending = false;

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(Long phoneno) {
		this.phoneno = phoneno;
	}

	public String[] getSlotAvailibility() {
		return slotAvailibility;
	}

	public void setSlotAvailibility(String[] slotAvailibility) {
		this.slotAvailibility = slotAvailibility;
	}

	public Boolean getSlotsPending() {
		return slotsPending;
	}

	public void setSlotsPending(Boolean slotsPending) {
		this.slotsPending = slotsPending;
	}
	
	
	
}