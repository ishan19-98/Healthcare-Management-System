package com.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		Optional<Doctor> result = doctorRepository.findById(Doctor.getDid());
		if(result.isPresent())
		{
			return "Doctor " + result.get().getDname() + " with given id already exists!";
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
		return "Doctor " + Doctor.getDname() + " details stored successfully";
		}
		else
		{
			return "Error occured while creating slots for doctor "+ Doctor.getDname();
		}
		}
	}

	public List<String> findDoctorSchedule(int did) {
		List<String> timeslot = restTemplate.getForObject("http://APPOINTMENT-MICRO-SERVICE/slot/getslotsbydoctorid/"+did, List.class);
        return timeslot;
		
	}

	public Optional<Doctor> findDoctorById(int did) {
		Optional<Doctor> doctor = doctorRepository.findById(did);
        return doctor;
		
	}
	
	/*
	 * This method is used to update basic doctor details like name,age, phone number.
	 * Slot details are updated using differant api hence this method do not update slot details.
	 */
	public String updateDoctorDetails(Doctor Doctor) {
		Optional<Doctor> result = doctorRepository.findById(Doctor.getDid());
		if(result.isEmpty())
		{
			return "Doctor " + result.get().getDname() + " with given id doesn't exists!";
		}
		else
		{
			Doctor Doctornew = new Doctor();
			Doctornew.setDid(Doctor.getDid());
			
			if(Doctor.getDname()!=null)
			 Doctornew.setDname(Doctor.getDname());
			else
			 Doctornew.setDname(result.get().getDname());
			
			if(Doctor.getAge()!= 0)
			 Doctornew.setAge(Doctor.getAge());
			else
			 Doctornew.setAge(result.get().getAge());
			
			if(Doctor.getPhoneno() != 0)
			 Doctornew.setPhoneno(Doctor.getPhoneno());
			else
			 Doctornew.setPhoneno(result.get().getPhoneno());	
			
			Doctornew.setSlotAvailibility(result.get().getSlotAvailibility());
			
			doctorRepository.saveAndFlush(Doctornew);
			return "Doctor " + Doctornew.getDname() + " details updates successfully";
			
		}
	}

	public int addSlotDetails(Doctor Doctor) {
		Optional<Doctor> result = doctorRepository.findById(Doctor.getDid());
		if(result.isEmpty())
		{
			return 0;
		}
		else
		{
			Doctor Doctornew = new Doctor();
			Doctornew.setDid(result.get().getDid());
			Doctornew.setDname(result.get().getDname());
			Doctornew.setAge(result.get().getAge());
			Doctornew.setPhoneno(result.get().getPhoneno());
			
//			List<String> newSlots = Arrays.asList(Doctor.getSlotAvailibility());
//			
//			// Make a mutable list from the old slots
//			List<String> oldslots = new ArrayList<>(Arrays.asList(result.get().getSlotAvailibility()));
//
//
//			oldslots.addAll(newSlots);
//			
//			// Convert to Set (remove duplicates) -> String[]
//			String[] mergedSlots = oldslots.stream().sorted()
//			                               .collect(Collectors.toSet())
//			                               .toArray(new String[0]);
//			
//			Doctornew.setSlotAvailibility(mergedSlots);
			Doctornew.setSlotAvailibility(Doctor.getSlotAvailibility());
			doctorRepository.saveAndFlush(Doctornew);
			
			//updating slots in slots repository
			MyRequest request = new MyRequest();
	        request.setParam1(Doctornew.getDid());
	        request.setParam2(Doctornew.getSlotAvailibility());
			int slotscreated = restTemplate.postForObject("http://APPOINTMENT-MICRO-SERVICE/slot/create", request, Integer.class);
			if(slotscreated==1)
			 return 1;
			else
		     return 2;		
			
		}
	}
	
}
