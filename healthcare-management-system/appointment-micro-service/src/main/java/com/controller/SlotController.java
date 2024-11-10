package com.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.MyRequest;
import com.entity.Slot;
import com.service.SlotService;

@RestController
@RequestMapping("slot")
public class SlotController {

	@Autowired
	SlotService slotService;
	
	@PostMapping(value = "create")
	public int createSlot(@RequestBody MyRequest myrequest)
	{
		return slotService.createSlot(myrequest);
	}
	
	@GetMapping(value="getslotsbydoctorid/{did}")
	public LocalTime[] findSlotsByDoctorId(@PathVariable("did") int did)
	{
		return slotService.findSlotsByDoctorId(did);
	}
}
