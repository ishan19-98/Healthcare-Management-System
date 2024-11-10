package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Appointment;
import com.entity.Bill;
import com.repository.BillRepository;

@Service
public class BillService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	AppointmentFeignService appointmentFeignService;
	
	public String createBill(Bill bill) {
		
		Appointment appointment = appointmentFeignService.findAppointmentById(bill.getAid());
		if(appointment !=null)
		{
			Bill bl=new Bill();
			bl.setAid(bill.getAid());
			bl.setPatientName(appointment.getPatientName());
			bl.setDoctorName(appointment.getDoctorName());
			bl.setBillamount(appointment.getAmount());
			billRepository.save(bl);
			return "Bill generated successfully for Appointment Id "+bill.getAid()+" with bill id "
					+bl.getId();
		}
		else
		{
			return "There was no appointment scheduled with this id";
		}
		
	}

	public Optional<Bill> findBillById(String id) {
		// TODO Auto-generated method stub
		return billRepository.findById(id);
	}



}
