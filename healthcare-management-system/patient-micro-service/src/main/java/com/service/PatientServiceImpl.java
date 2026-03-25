package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bean.PatientDTO;
import com.entity.Patient;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);
	
	public PatientServiceImpl(PatientRepository patientRepository)
	{
		logger.info("Service started...");
		this.patientRepository=patientRepository;
	}

	@Override
	public String storePatient(PatientDTO patientDto) throws GlobalException, ConflictException {
		Patient patient=convertBeanToEntity(patientDto);
		logger.debug("Searching patient details with patient id "+patientDto.getPid());
		Optional<Patient> result = patientRepository.findById(patient.getPid());
		if(result.isPresent())
		{
			logger.error("Patient detail already exists"+result);
			throw new ConflictException("Patient with given id already exists! Try adding patient using some other patient id.");
		}
		else
		{
			logger.debug("Saving patient details with patient id "+patientDto.getPid());
			patientRepository.save(patient);
			logger.debug("Patient "+patient+" details saved successfully in database");
		    return "Patient "+ patient.getPname() + " details stored successfully";
		}
	}

	@Override
	public PatientDTO findPatientById(Integer id) {
		return convertEntityToBean(patientRepository.findById(id).get());
	}
	
	@Override
	public PatientDTO findPatientByPhoneNumber(Long phoneno) {
		return convertEntityToBean(patientRepository.findPatientByPhoneNumber(phoneno).get());
	}

	/*
	 * Update patient details with the new details fetched from request body.
	 * Details not passed will remain same as before.
	 */
	@Override
	public String updatePatientDetails(PatientDTO patientDto) throws ResourceNotFoundException {
		Patient patient=convertBeanToEntity(patientDto);
		logger.debug("Searching patient details with patient id"+patientDto.getPid());
		Optional<Patient> result = patientRepository.findById(patient.getPid());
		if(result.isEmpty())
		{
			logger.error("Patient details with given id "+patient.getPid()+" not found");
			throw new ResourceNotFoundException("Patient " + result.get().getPname() + " with given id doesn't exists!");
		}
		else
		{
			logger.debug("Patient details with patient id "+patientDto.getPid()+" retrieved successfully");
			Patient patientnew = new Patient();
			
			logger.debug("Setting patient id to "+patient.getPid());
			patientnew.setPid(patient.getPid());
			
			if (patient.getPname() != null) {
				logger.debug("Setting patient id to " + patient.getPid());
				patientnew.setPname(patient.getPname());
			} else {
				logger.debug("Setting patient id to " + patient.getPid());
				patientnew.setPname(result.get().getPname());
			}
			
			if(patient.getAge()!=0) {
			 logger.debug("Setting patient age to " + patient.getAge());
			 patientnew.setAge(patient.getAge());}
			else {
			 logger.debug("Setting patient age to " + patient.getAge());
			 patientnew.setAge(result.get().getAge());
			}
			if(patient.getPhoneno()!=0) {
			 logger.debug("Setting patient phoneno to " + patient.getPhoneno());
			 patientnew.setPhoneno(patient.getPhoneno());}
			else {
		     logger.debug("Setting patient phoneno to " + patient.getPhoneno());
			 patientnew.setPhoneno(result.get().getPhoneno());}
			
			logger.debug("Saving updated patient details "+patientnew);
			patientRepository.saveAndFlush(patientnew);
			logger.debug("Patient "+patientnew.getPname()+" details saved successfully in database");
			return "Patient "+ patientnew.getPname() + " details updated successfully";
		}
	}

	@Override
	public Page<PatientDTO> findAllPatients(Pageable pageable) {
		logger.debug("Retrieving all Patient details");
		return patientRepository.findAll(pageable).map(PatientServiceImpl::convertEntityToBean);
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

	@Override
	public String predictPatientAvailability(int distanceFromClinicKm, int previousNumberOfAppointments,
			int pastNoShowCount) throws IOException {
		
		
		//invoke the ML model using a python script
		ProcessBuilder pb = new ProcessBuilder(
	            "python",
	            "predict.py",
	            String.valueOf(distanceFromClinicKm),
	            String.valueOf(previousNumberOfAppointments),
	            String.valueOf(pastNoShowCount)
	    );

	    Process process = pb.start();

	    BufferedReader reader =
	            new BufferedReader(
	                    new InputStreamReader(process.getInputStream()));

	    String result = reader.readLine();

	    if(Integer.parseInt(result)==1)
	    {
	    	return "Patient is likely to attend the appointment";
	    }
	    else
	    {
	    	return "Patient is likely to miss the appointment";
	    }

	}

}
