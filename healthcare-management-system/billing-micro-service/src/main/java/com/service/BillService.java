package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.entity.Appointment;
import com.entity.Bill;
import com.repository.BillRepository;

@Service
public class BillService {

	@Autowired
	BillRepository billRepository;
	
	
    @KafkaListener(topics = "billing-topic",groupId = "billing-service")
	public void generateBill(Appointment appointment) {
		
		if(appointment !=null)
		{
			Bill bill=new Bill();
			bill.setAid(appointment.aid());
			bill.setPatientName(appointment.patientName());
			bill.setDoctorName(appointment.doctorName());
			bill.setBillamount(appointment.amount());
			billRepository.save(bill);
			System.out.println("Bill generated successfully for Appointment Id "+bill.getAid()+" with bill id "
					+bill.getBillId());
		}
	}

	public Optional<Bill> findBillByAppointmentId(Integer id) {
		// TODO Auto-generated method stub
		return billRepository.findById(id);
	}



}
