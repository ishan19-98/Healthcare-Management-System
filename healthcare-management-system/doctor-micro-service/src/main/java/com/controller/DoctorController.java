package com.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorDTO;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.service.DoctorService;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("doctors")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@PostMapping(value = "",consumes = MediaType.APPLICATION_JSON)
	public String storedoctor(@Valid @RequestBody DoctorDTO doctor) throws GlobalException, ConflictException
	{
		return doctorService.storeDoctor(doctor);
		
	}
	
	@GetMapping(value = "schedule/{did}",produces = MediaType.APPLICATION_JSON)
	public Set<String> findDoctorSchedule(@PathVariable int did)
	{
		return doctorService.findDoctorSchedule(did);
	}
	
	@GetMapping(value = "/{did}",produces = MediaType.APPLICATION_JSON)
	public DoctorDTO findDoctorById(@PathVariable int did)
	{
		return doctorService.findDoctorById(did);	
	}
	
	@PutMapping(value = "",consumes = MediaType.APPLICATION_JSON)
	public String updatedoctorDetails(@RequestBody DoctorDTO doctor) throws ResourceNotFoundException
	{
		return doctorService.updateDoctorDetails(doctor);
	}
	
	@PostMapping(value = "slot",consumes = MediaType.APPLICATION_JSON)
	public String addSlotDetails(@RequestBody DoctorDTO doctor) throws GlobalException, ResourceNotFoundException
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
