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
import com.service.AppointmentService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@PostMapping(value = "create",consumes = MediaType.APPLICATION_JSON)
	public String storeappointment(@RequestBody Appointment appointment)
	{
		return appointmentService.createAppointment(appointment);
		
	}
	
	@GetMapping(value = "findbyid/{id}",produces = MediaType.APPLICATION_JSON)
	public Optional<Appointment> findappointment(@PathVariable("id") Integer id)
	{
		return appointmentService.findAppointmentById(id);
		
	}
	
	@PutMapping(value = "update",consumes = MediaType.APPLICATION_JSON)
	public String updateappointmentDetails(@RequestBody Appointment appointment)
	{
		return appointmentService.updateAppointmentDetails(appointment);
		
	}
	
	@DeleteMapping(value = "cancel/{aid}")
	public String cancelAppointmentById(@PathVariable("aid") Integer aid)
	{
		return appointmentService.deleteAppointmentById(aid);
		
	}
	

}
