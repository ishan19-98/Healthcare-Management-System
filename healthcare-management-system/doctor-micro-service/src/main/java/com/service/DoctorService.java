package com.service;

import java.util.Optional;
import java.util.Set;

import com.bean.DoctorDTO;
import com.entity.Doctor;
import com.exception.ConflictException;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;

public interface DoctorService {
	
	public String storeDoctor(DoctorDTO Doctor) throws GlobalException, ConflictException;
	
	public Set<String> findDoctorSchedule(int did);
	
	public DoctorDTO findDoctorById(int did);
	
	public String updateDoctorDetails(DoctorDTO Doctor) throws ResourceNotFoundException;
	
	public int addSlotDetails(DoctorDTO Doctor);

}
