package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Patient;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;
	
	public String storePatient(Patient patient) throws GlobalException, ConflictException {
		Optional<Patient> result = patientRepository.findById(patient.getPid());
		if(result.isPresent())
		{
			throw new ConflictException("Patient with given id already exists! Try adding patient using some other patient id.");
		}
		else
		{
			patientRepository.save(patient);
		    return "Patient "+ patient.getPname() + " details stored successfully";
		}
	}

	public Optional<Patient> findPatientById(Integer id) {
		return patientRepository.findById(id);
	}

	/*
	 * Update patient details with the new details fetched from request body.
	 * Details not passed will remain same as before.
	 */
	public String updatePatientDetails(Patient patient) throws ResourceNotFoundException {
		Optional<Patient> result = patientRepository.findById(patient.getPid());
		if(result.isEmpty())
		{
			throw new ResourceNotFoundException("Patient " + result.get().getPname() + " with given id doesn't exists!");
		}
		else
		{
			Patient patientnew = new Patient();
			patientnew.setPid(patient.getPid());
			
			if(patient.getPname()!=null)
			 patientnew.setPname(patient.getPname());
			else
			 patientnew.setPname(result.get().getPname());
			
			if(patient.getAge()!=0)
			 patientnew.setAge(patient.getAge());
			else
			 patientnew.setAge(result.get().getAge());
			
			if(patient.getPhoneno()!=0)
			 patientnew.setPhoneno(patient.getPhoneno());
			else
			 patientnew.setPhoneno(result.get().getPhoneno());
			
			patientRepository.saveAndFlush(patientnew);
			return "Patient "+ patientnew.getPname() + " details updated successfully";
		}
	}

}
