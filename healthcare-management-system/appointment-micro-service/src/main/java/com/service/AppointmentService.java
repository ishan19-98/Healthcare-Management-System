package com.service;

import java.util.Optional;

import com.entity.Appointment;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;

public interface AppointmentService {

	public String createAppointment(Appointment appointment) throws GlobalException, ResourceNotFoundException;
	
	public Optional<Appointment> findAppointmentById(Integer id);
	
	public String updateAppointmentDetails(Appointment appointment) throws ResourceNotFoundException, GlobalException;
	
	public String deleteAppointmentById(int aid) throws GlobalException, ResourceNotFoundException;
}
