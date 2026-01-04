package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	@Query(value = "Select d from Doctor d where d.slotsPending=true")
	public List<Doctor> getDoctorsWithSlotsPending();

}
