package com.service;

import java.time.LocalTime;

import com.bean.MyRequest;

public interface SlotService {
	
	public int createSlot(MyRequest myrequest);
	
	public LocalTime[] findSlotsByDoctorId(int did);

}
