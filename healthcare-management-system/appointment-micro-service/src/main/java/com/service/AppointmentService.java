package com.service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	SlotRepository slotRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public String createAppointment(Appointment appointment) throws GlobalException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		Patient patient = restTemplate.getForObject("http://PATIENT-MICRO-SERVICE/patient/findbyid/"+appointment.getPid(), Patient.class);
		if(patient != null)
		{
			Doctor doctor = restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctor/findbyid/"+appointment.getDid(), Doctor.class);
            if(doctor != null)
            {
            	String timeslot = appointment.getTimeslot();
            	Slot slot = slotRepository.getSlotByTime(LocalTime.parse(timeslot));
            	if(slot==null)
            	{
            		return "There is no available slot exists for given time. Try booking some other time slot.";
            	}
            	if(!slot.isBookFlag())
            	{
            		appointment.setPatientName(patient.getPname());
            		appointment.setDoctorName(doctor.getDname());
            		appointmentRepository.save(appointment);
            		slotRepository.updateSlotDetails(true, slot.getSid(),doctor.getDid());
            		String[] timeAry = doctor.getSlotAvailibility();
            		List<String> timeLst = new ArrayList<>(Arrays.asList(timeAry));            		
            		timeLst.remove(timeslot);
            		String[] array = timeLst.toArray(new String[0]);
            		doctor.setSlotAvailibility(array);
            		String docUpdated = restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctor/addSlot", doctor, String.class);
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
            		return "This time slot is already booked, try booking another slot!";
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
		// TODO Auto-generated method stub
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
    			Doctor doctor = restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctor/findbyid/"+docId, Doctor.class);
                if(doctor != null)
                {
                	String[] timeAry = doctor.getSlotAvailibility();
            		List<String> timeLst = new ArrayList<>(Arrays.asList(timeAry));            		
            		timeLst.add(oldtimeslot);
            		String[] array = timeLst.toArray(new String[0]);
            		doctor.setSlotAvailibility(array);
            		String docUpdated = restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctor/addSlot", doctor, String.class);
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
        		return "This slot is already occupied, Try booking for some other slot";
        	}
			
		}
	}

	public String deleteAppointmentById(int aid) throws GlobalException, ResourceNotFoundException {
		// TODO Auto-generated method stub
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
			Doctor doctor = restTemplate.getForObject("http://DOCTOR-MICRO-SERVICE/doctor/findbyid/"+docId, Doctor.class);
            if(doctor != null)
            {
            	String[] timeAry = doctor.getSlotAvailibility();
        		List<String> timeLst = new ArrayList<>(Arrays.asList(timeAry));            		
        		timeLst.add(oldtimeslot);
        		String[] array = timeLst.toArray(new String[0]);
        		doctor.setSlotAvailibility(array);
        		String docUpdated = restTemplate.postForObject("http://DOCTOR-MICRO-SERVICE/doctor/addSlot", doctor, String.class);
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
