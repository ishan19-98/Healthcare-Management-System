package com.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Doctor;
import com.service.DoctorService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@PostMapping(value = "store",consumes = MediaType.APPLICATION_JSON)
	public String storedoctor(@RequestBody Doctor doctor)
	{
		return doctorService.storeDoctor(doctor);
		
	}
	
	@GetMapping(value = "schedule/{did}",produces = MediaType.APPLICATION_JSON)
	public List<String> findDoctorSchedule(@PathVariable int did)
	{
		return doctorService.findDoctorSchedule(did);
		
	}
	
	@GetMapping(value = "findbyid/{did}",produces = MediaType.APPLICATION_JSON)
	public Optional<Doctor> findDoctorById(@PathVariable int did)
	{
		return doctorService.findDoctorById(did);
		
	}
	
	@PutMapping(value = "update",consumes = MediaType.APPLICATION_JSON)
	public String updatedoctorDetails(@RequestBody Doctor doctor)
	{
		return doctorService.updateDoctorDetails(doctor);
		
	}
	
	@PostMapping(value = "updateSlot",consumes = MediaType.APPLICATION_JSON)
	public int updateSlotDetails(@RequestBody Doctor doctor)
	{
		return doctorService.updateSlotDetails(doctor);
		
	}
	
	

}
