package com.service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bean.Doctor;
import com.bean.Patient;
import com.entity.Appointment;
import com.entity.Slot;
import com.exception.ResourceNotFoundException;
import com.exception.GlobalException;
import com.repository.AppointmentRepository;
import com.repository.SlotRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	SlotRepository slotRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public String createAppointment(Appointment appointment) throws GlobalException, ResourceNotFoundException {
		Patient patient = restTemplate.getForObject("http://PATIENT-MICRO-SERVICE/patients/"+appointment.getPid(), Patient.class);
		if(patient != null)
		{
			Doctor doctor = restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctors/"+appointment.getDid(), Doctor.class);
            if(doctor != null)
            {
            	String timeslot = appointment.getTimeslot();
            	Slot slot = slotRepository.getSlotByTime(LocalTime.parse(timeslot));
            	if(slot==null)
            	{
        			throw new ResourceNotFoundException("There is no available slot exists for given time. Try booking some other time slot.");
            	}
            	if(!slot.isBookFlag())
            	{
            		appointment.setPatientName(patient.getPname());
            		appointment.setDoctorName(doctor.getDname());
            		appointmentRepository.save(appointment);
            		slotRepository.updateSlotDetails(true, slot.getSid(),doctor.getDid());
            		String[] timeAry = doctor.getSlotAvailibility();
            		Set<String> timeLst = new LinkedHashSet<>(Arrays.asList(timeAry)); 
            		timeLst.remove(timeslot);
            		List<String> timeLstUpdated=timeLst.stream().sorted().collect(Collectors.toList());
            		String[] array = timeLstUpdated.toArray(new String[0]);
            		doctor.setSlotAvailibility(array);
            		String docUpdated = restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctors/slot", doctor, String.class);
            		if(docUpdated.equalsIgnoreCase("Slot has been added successfully"))
            		{
            			return "Appointment Created Successfully";
            		}
            		else
            		{
            			throw new GlobalException("Error occurred while creating appointment");
            		}
            	}
            	else
            	{
        			throw new ResourceNotFoundException("This slot is already occupied, Try booking for some other slot");
            	}
            }
            else
            {
    			throw new ResourceNotFoundException("Doctor Record Doesnot exists");
            }
		}
		else
		{
			throw new ResourceNotFoundException("Patient Record Doesnot exists");
		}
	}

	public Optional<Appointment> findAppointmentById(Integer id) {
		return appointmentRepository.findById(id);
	}

	public String updateAppointmentDetails(Appointment appointment) throws ResourceNotFoundException, GlobalException {
		Optional<Appointment> appointmentDb = appointmentRepository.findById(appointment.getAid());
		if(appointmentDb.isEmpty())
		{
			throw new ResourceNotFoundException("Appointment with given id doesn't exists!");
		}
		else
		{
			String newtimeslot = appointment.getTimeslot();
			String oldtimeslot = appointmentRepository.getSlotIdById(appointment.getAid());
        	Slot newslot = slotRepository.getSlotByTime(LocalTime.parse(newtimeslot));
        	Slot oldslot = slotRepository.getSlotByTime(LocalTime.parse(oldtimeslot));
        	if(!newslot.isBookFlag())
        	{
        		Appointment appointmentnew=appointmentRepository.getById(appointment.getAid());
        		slotRepository.updateSlotDetails(true, newslot.getSid(),Integer.parseInt(appointmentnew.getDid()));
        		slotRepository.updateSlotDetails(false, oldslot.getSid(),Integer.parseInt(appointmentnew.getDid()));
        		appointmentRepository.updateAppointmentTime(newtimeslot, appointment.getAid());
        		
        		int docId= Integer.parseInt(appointmentDb.get().getDid());
    			Doctor doctor = restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctors/"+docId, Doctor.class);
                if(doctor != null)
                {
                	String[] timeAry = doctor.getSlotAvailibility();
                	Set<String> timeLst = new LinkedHashSet<>(Arrays.asList(timeAry));  
            		timeLst.add(oldtimeslot);
            		timeLst.remove(newtimeslot);
            		List<String> timeLstUpdated=timeLst.stream().sorted().collect(Collectors.toList());
            		String[] array = timeLstUpdated.toArray(new String[0]);
            		doctor.setSlotAvailibility(array);
            		String docUpdated = restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctors/slot", doctor, String.class);
            		if(docUpdated.equalsIgnoreCase("Slot has been added successfully"))
            		{
            			return "Appointment Updated Successfully";
            		}
            		else
            		{
            			throw new GlobalException("Error occurred while creating appointment");
            		}  
            	}
    			throw new GlobalException("Error Encountered While Updating Appointment");
        	}
        	else
        	{
    			throw new ResourceNotFoundException("This slot is already occupied, Try booking for some other slot");
        	}
			
		}
	}

	public String deleteAppointmentById(int aid) throws GlobalException, ResourceNotFoundException {
		Optional<Appointment> appointment = appointmentRepository.findById(aid);
		if(appointment.isEmpty())
		{
			throw new ResourceNotFoundException("Appointment with given id doesn't exists!");
		}
		else
		{
			String oldtimeslot = appointmentRepository.getSlotIdById(aid);
        	Slot oldslot = slotRepository.getSlotByTime(LocalTime.parse(oldtimeslot));
        	slotRepository.updateSlotDetails(false, oldslot.getSid(),Integer.parseInt(appointment.get().getDid()));
			appointmentRepository.deleteById(aid); 
			
			int docId= Integer.parseInt(appointment.get().getDid());
			Doctor doctor = restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctors/"+docId, Doctor.class);
            if(doctor != null)
            {
            	String[] timeAry = doctor.getSlotAvailibility();
        		Set<String> timeLst = new LinkedHashSet<>(Arrays.asList(timeAry));            		
        		timeLst.add(oldtimeslot);
        		List<String> timeLstUpdated=timeLst.stream().sorted().collect(Collectors.toList());
        		String[] array = timeLstUpdated.toArray(new String[0]);
        		doctor.setSlotAvailibility(array);
        		String docUpdated = restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctors/slot", doctor, String.class);
        		if(docUpdated.equalsIgnoreCase("Slot has been added successfully"))
        		{
        			return "Appointment deleted successfully";
        		}
        		else
        		{
        			throw new GlobalException("Error occurred while cancelling appointment");
        		}            
        		}
            else
            {
    			throw new GlobalException("Error Occured While Updating Doctor's Slot");
            }
	       
		}
	}

}
