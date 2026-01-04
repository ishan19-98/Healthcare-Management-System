package com.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bean.PatientDTO;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;

public interface PatientService {

	public String storePatient(PatientDTO patientDto) throws GlobalException, ConflictException;
	
	public PatientDTO findPatientById(Integer id);
	
	public PatientDTO findPatientByPhoneNumber(Long phoneno);
	
	public String updatePatientDetails(PatientDTO patientDto) throws ResourceNotFoundException;
	
	public Page<PatientDTO> findAllPatients(Pageable pageable);
		
}
