package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.Doctor;
import com.bean.Patient;
import com.entity.Appointment;
import com.exception.ResourceNotFoundException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class AppointmentRemoteClient {

	private RestTemplate restTemplate;
	
	public AppointmentRemoteClient(RestTemplate restTemplate)
	{
		this.restTemplate=restTemplate;
	}
	
	@CircuitBreaker(name = "PATIENT-FETCH-CIRCUIT-BREAKER", fallbackMethod = "fetchPatientFallBack")
	public Patient fetchPatient(Appointment appointment)
	{
		return restTemplate.getForObject("http://PATIENT-MICRO-SERVICE/patients/"+appointment.getPid(), Patient.class);
	}
	
	@CircuitBreaker(name = "DOCTOR-FETCH-CIRCUIT-BREAKER", fallbackMethod = "fetchDoctorFallBack")
	public Doctor fetchDoctor(int Did)
	{
		return 	restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctors/"+Did, Doctor.class);
	}
	
	@CircuitBreaker(name = "DOCTOR-SLOTS-SYNC-CIRCUIT-BREAKER", fallbackMethod = "updateDoctorSlotsFallBack")
	public String updateDoctorSlots(Doctor doctor)
	{
		return 	restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctors/slot", doctor, String.class);
	}
	
	
	public Patient fetchPatientFallBack(Appointment appointment, Throwable ex) throws ResourceNotFoundException
	{
		throw new ResourceNotFoundException("Unable to fetch Patient details. Either Patient Service is DOWN or Patient record doesnot exists in database!");
	}
	
	public Doctor fetchDoctorFallBack(int Did, Throwable ex) throws ResourceNotFoundException
	{
		throw new ResourceNotFoundException("Unable to fetch Doctor details. Doctor Service is DOWN or Doctor record doesnot exists in database!");
	}
	
	public Doctor updateDoctorSlotsFallBack(Doctor doctor, Throwable ex) throws ResourceNotFoundException
	{
		throw new ResourceNotFoundException("Unable to update Doctor slots. Doctor Service is DOWN!");
	}

	
}
