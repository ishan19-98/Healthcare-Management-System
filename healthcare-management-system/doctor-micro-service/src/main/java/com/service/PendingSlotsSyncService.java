package com.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bean.MyRequest;
import com.entity.Doctor;
import com.repository.DoctorRepository;

@Component
public class PendingSlotsSyncService {

	
	private  DoctorRepository doctorRepository = null;
    private  RestTemplate restTemplate = null;

    public PendingSlotsSyncService(DoctorRepository doctorRepository, RestTemplate restTemplate) {
        this.doctorRepository = doctorRepository;
        this.restTemplate = restTemplate;
    }
    
	@Scheduled(fixedDelay = 15000)
	public void SyncSlotsWithAppointmentService() {
		List<Doctor> doctors = doctorRepository.getDoctorsWithSlotsPending();

		for (Doctor doctor : doctors) {
			try {
				MyRequest request = new MyRequest();
				request.setParam1(doctor.getDid());
				request.setParam2(doctor.getSlotAvailibility());
				int slotscreated = restTemplate.postForObject("http://APPOINTMENT-MICRO-SERVICE/slots", request,
						Integer.class);
				if (slotscreated == 1) {
					doctor.setSlotsPending(false);
					doctorRepository.save(doctor);
					System.out.println("Slots synced successfully for Doctor" + doctor.getDname());
				}
			} catch (Exception e) {
				System.out.println("Retry failed for doctor " + doctor.getDname() + ". Will try again later.");
			}
		}
	}

}
