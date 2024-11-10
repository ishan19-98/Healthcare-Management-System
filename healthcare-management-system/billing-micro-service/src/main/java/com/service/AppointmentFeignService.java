package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bean.Appointment;

@FeignClient(name = "APPOINTMENT-MICRO-SERVICE")
public interface AppointmentFeignService {
	
	@GetMapping(value = "appointment/findbyid/{aid}")
	public Appointment findAppointmentById(@PathVariable("aid") int aid);

}
