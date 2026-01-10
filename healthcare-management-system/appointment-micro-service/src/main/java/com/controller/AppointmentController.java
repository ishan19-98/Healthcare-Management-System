package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Appointment;
import com.exception.GlobalException;
import com.exception.ResourceNotFoundException;
import com.service.AppointmentService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
	
	private AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService)
	{
		this.appointmentService=appointmentService;
	}
	
	@PostMapping(value = "",consumes = MediaType.APPLICATION_JSON)
	public String storeappointment(@RequestBody Appointment appointment) throws GlobalException, ResourceNotFoundException
	{
		return appointmentService.createAppointment(appointment);
		
	}
	
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON)
	public Optional<Appointment> findappointment(@PathVariable("id") Integer id)
	{
		return appointmentService.findAppointmentById(id);
	}
	
	@PutMapping(value = "",consumes = MediaType.APPLICATION_JSON)
	public String updateappointmentDetails(@RequestBody Appointment appointment) throws ResourceNotFoundException, GlobalException
	{
		return appointmentService.updateAppointmentDetails(appointment);
		
	}
	
	@DeleteMapping(value = "/{aid}")
	public String cancelAppointmentById(@PathVariable("aid") Integer aid) throws ResourceNotFoundException, GlobalException
	{
		return appointmentService.deleteAppointmentById(aid);
		
	}
	

}
