package com.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.MyRequest;
import com.entity.Doctor;
import com.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public String storeDoctor(Doctor Doctor) {
		// TODO Auto-generated method stub
		Optional<Doctor> result = doctorRepository.findById(Doctor.getDid());
		if(result.isPresent())
		{
			return "Doctor with given id already exists";
		}
		else
		{   
		MyRequest request = new MyRequest();
        request.setParam1(Doctor.getDid());
        request.setParam2(Doctor.getSlotAvailibility());
		int slotscreated = restTemplate.postForObject("http://APPOINTMENT-MICRO-SERVICE/slot/create", request, Integer.class);
		if(slotscreated==1)
		{
		doctorRepository.save(Doctor);
		return "Doctor details stored successfully";
		}
		else
		{
			return "Issues while creating slots for the given doctor";
		}
		}
	}

	public List<String> findDoctorSchedule(int did) {
		// TODO Auto-generated method stub
		List<String> timeslot = restTemplate.getForObject("http://APPOINTMENT-MICRO-SERVICE/slot/getslotsbydoctorid/"+did, List.class);
        return timeslot;
		
	}

	public Optional<Doctor> findDoctorById(int did) {
		// TODO Auto-generated method stub
		Optional<Doctor> doctor = doctorRepository.findById(did);
        return doctor;
		
	}
	
	public String updateDoctorDetails(Doctor Doctor) {
		Optional<Doctor> result = doctorRepository.findById(Doctor.getDid());
		if(result.isEmpty())
		{
			return "Doctor with given id doesn't exists!";
		}
		else
		{
			Doctor Doctornew = new Doctor();
			Doctornew.setDid(Doctor.getDid());
			Doctornew.setDname(Doctor.getDname());
			Doctornew.setAge(Doctor.getAge());
			Doctornew.setPhoneno(Doctor.getPhoneno());
			doctorRepository.saveAndFlush(Doctornew);
			return "Doctor details updates successfully";
			
		}
	}

	public int updateSlotDetails(Doctor Doctor) {
		Optional<Doctor> result = doctorRepository.findById(Doctor.getDid());
		if(result.isEmpty())
		{
			return 0;
		}
		else
		{
			Doctor Doctornew = new Doctor();
			Doctornew.setDid(Doctor.getDid());
			Doctornew.setDname(Doctor.getDname());
			Doctornew.setAge(Doctor.getAge());
			Doctornew.setPhoneno(Doctor.getPhoneno());
			Doctornew.setSlotAvailibility(Doctor.getSlotAvailibility());
			doctorRepository.saveAndFlush(Doctornew);
			return 1;
			
		}
	}
	
}
