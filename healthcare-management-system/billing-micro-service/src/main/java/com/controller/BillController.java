package com.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Bill;
import com.service.BillService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("billing")
public class BillController {
	
	private BillService billService;
	
	public BillController(BillService billService)
	{
		this.billService=billService;
	}
	
//	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON)
//	public Optional<Bill> findBillByAppointmentId(@PathVariable("id") Integer id)
//	{
//		
//	}
//	
}


