package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Patient;
import com.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;
	
	public String storePatient(Patient patient) {
		// TODO Auto-generated method stub
		Optional<Patient> result = patientRepository.findById(patient.getPid());
		if(result.isPresent())
		{
			return "Patient with given id already exists";
		}
		else
		{
			patientRepository.save(patient);
		    return "Patient details stored successfully";
		}
	}

	public Optional<Patient> findPatientById(Integer id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id);
	}

	public String updatePatientDetails(Patient patient) {
		Optional<Patient> result = patientRepository.findById(patient.getPid());
		if(result.isEmpty())
		{
			return "Patient with given id doesn't exists!";
		}
		else
		{
			Patient patientnew = new Patient();
			patientnew.setPid(patient.getPid());
			patientnew.setPname(patient.getPname());
			patientnew.setAge(patient.getAge());
			patientnew.setPhoneno(patient.getPhoneno());
			patientRepository.saveAndFlush(patientnew);
			return "Patient details updates successfully";
			
		}
	}

}
