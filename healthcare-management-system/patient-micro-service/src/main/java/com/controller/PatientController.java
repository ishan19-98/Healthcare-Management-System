package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.PatientDTO;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.service.PatientService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("patients")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping(value = "",consumes = MediaType.APPLICATION_JSON)
	public String storePatient(@Valid @RequestBody PatientDTO patient) throws GlobalException, ConflictException
	{
		return patientService.storePatient(patient);
		
	}
	
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON)
	public PatientDTO findPatient(@PathVariable("id") @Positive Integer id)
	{
		return patientService.findPatientById(id);
		
	}
	
	@PutMapping(value = "",consumes = MediaType.APPLICATION_JSON)
	public String updatePatientDetails(@RequestBody PatientDTO patient) throws ResourceNotFoundException
	{
		return patientService.updatePatientDetails(patient);
		
	}
	
	@GetMapping(value = "",produces = MediaType.APPLICATION_JSON)
	public Page<PatientDTO> findAllPatient(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "pid") String sortBy,
			@RequestParam(defaultValue = "asc") String direction)
	{
		
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		
		Pageable pageable = PageRequest.of(page, size, sort);
		return patientService.findAllPatients(pageable);
		
	}
	
	@GetMapping(value = "phone/{phoneno}",produces = MediaType.APPLICATION_JSON)
	public PatientDTO findPatientByPhoneNo(@PathVariable("phoneno") Long phoneno)
	{
		return patientService.findPatientByPhoneNumber(phoneno);		
	}

}
