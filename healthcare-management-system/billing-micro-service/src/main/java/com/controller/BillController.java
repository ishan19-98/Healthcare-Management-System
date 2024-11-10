package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Bill;
import com.service.BillService;

import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("bill")
public class BillController {
	
	@Autowired
	BillService billService;
	
	@PostMapping(value = "create",consumes = MediaType.APPLICATION_JSON)
	public String storeBill(@RequestBody Bill bill)
	{
		return billService.createBill(bill);
		
	}
	
	@GetMapping(value = "findbyid/{id}",produces = MediaType.APPLICATION_JSON)
	public Optional<Bill> findBill(@PathVariable("id") String id)
	{
		return billService.findBillById(id);
		
	}
	
}
