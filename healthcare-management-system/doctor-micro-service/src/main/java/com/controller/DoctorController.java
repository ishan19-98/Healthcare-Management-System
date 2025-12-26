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
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.service.DoctorService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@PostMapping(value = "store",consumes = MediaType.APPLICATION_JSON)
	public String storedoctor(@RequestBody Doctor doctor) throws GlobalException, ConflictException
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
	public String updatedoctorDetails(@RequestBody Doctor doctor) throws ResourceNotFoundException
	{
		return doctorService.updateDoctorDetails(doctor);
		
	}
	
	@PostMapping(value = "addSlot",consumes = MediaType.APPLICATION_JSON)
	public String addSlotDetails(@RequestBody Doctor doctor) throws GlobalException, ResourceNotFoundException
	{
		int updateStatus =  doctorService.addSlotDetails(doctor); 
		
		if(updateStatus==1)
			return "Slot has been added successfully";
		else if(updateStatus==0)
			throw new ResourceNotFoundException("Doctor " + doctor.getDid() + " with given id doesn't exists!");
		else
			throw new GlobalException("Error Occured while adding slot!");
	}
	
	

}
