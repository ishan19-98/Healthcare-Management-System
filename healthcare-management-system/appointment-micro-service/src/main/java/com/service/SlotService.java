package com.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.MyRequest;
import com.entity.Slot;
import com.repository.SlotRepository;

@Service
public class SlotService {

	@Autowired
	SlotRepository slotRepository;

	public int createSlot(MyRequest myrequest) {
		// TODO Auto-generated method stub
		int length = myrequest.getParam2().length;
		for(int i=0;i<length;i++)
		{
		Slot slot = new Slot();
		slot.setDid(myrequest.getParam1());
		slot.setDate(LocalDate.now());
		slot.setTime(LocalTime.parse((CharSequence) myrequest.getParam2()[i]));
		slot.setBookFlag(false);
		slotRepository.save(slot);
		}
		
		return 1;
	}
	
	public LocalTime[] findSlotsByDoctorId(int did) {
		
		LocalTime[] timeslot = slotRepository.findSlotsByDoctorId(did);
		return timeslot;
	}
	
}
