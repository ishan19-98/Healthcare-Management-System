package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bean.PatientDTO;
import com.entity.Patient;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.repository.PatientRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;

	public String storePatient(PatientDTO patientDto) throws GlobalException, ConflictException {
		Patient patient=convertBeanToEntity(patientDto);
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

	public PatientDTO findPatientById(Integer id) {
		return convertEntityToBean(patientRepository.findById(id).get());
	}
	
	public PatientDTO findPatientByPhoneNumber(Long phoneno) {
		return convertEntityToBean(patientRepository.findPatientByPhoneNumber(phoneno).get());
	}

	/*
	 * Update patient details with the new details fetched from request body.
	 * Details not passed will remain same as before.
	 */
	public String updatePatientDetails(PatientDTO patientDto) throws ResourceNotFoundException {
		Patient patient=convertBeanToEntity(patientDto);
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

	public Page<PatientDTO> findAllPatients(Pageable pageable) {
		return patientRepository.findAll(pageable).map(PatientService::convertEntityToBean);
	}
	
	private static Patient convertBeanToEntity(PatientDTO patientDto)
	{
		Patient patient = new Patient();
		patient.setPid(patientDto.getPid());
		patient.setPname(patientDto.getPname());
		patient.setPhoneno(patientDto.getPhoneno());
		patient.setAge(patientDto.getAge());
		
		return patient;
	}
	
	private static PatientDTO convertEntityToBean(Patient patient)
	{
		PatientDTO patientDto = new PatientDTO();
		patientDto.setPid(patient.getPid());
		patientDto.setPname(patient.getPname());
		patientDto.setPhoneno(patient.getPhoneno());
		patientDto.setAge(patient.getAge());
		
		return patientDto;
	}

}
