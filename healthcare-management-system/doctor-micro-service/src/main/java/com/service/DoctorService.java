package com.service;

import java.util.Optional;
import java.util.Set;

import com.entity.Doctor;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;

public interface DoctorService {
	
	public String storeDoctor(Doctor Doctor) throws GlobalException, ConflictException;
	
	public Set<String> findDoctorSchedule(int did);
	
	public Optional<Doctor> findDoctorById(int did);
	
	public String updateDoctorDetails(Doctor Doctor) throws ResourceNotFoundException;
	
	public int addSlotDetails(Doctor Doctor);

}
