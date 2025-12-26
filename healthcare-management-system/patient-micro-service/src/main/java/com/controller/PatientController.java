package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Patient;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.service.PatientService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping(value = "store",consumes = MediaType.APPLICATION_JSON)
	public String storePatient(@RequestBody Patient patient) throws GlobalException
	{
		return patientService.storePatient(patient);
		
	}
	
	@GetMapping(value = "findbyid/{id}",produces = MediaType.APPLICATION_JSON)
	public Optional<Patient> findPatient(@PathVariable("id") Integer id)
	{
		return patientService.findPatientById(id);
		
	}
	
	@PutMapping(value = "update",consumes = MediaType.APPLICATION_JSON)
	public String updatePatientDetails(@RequestBody Patient patient) throws ResourceNotFoundException
	{
		return patientService.updatePatientDetails(patient);
		
	}
	
	

}
