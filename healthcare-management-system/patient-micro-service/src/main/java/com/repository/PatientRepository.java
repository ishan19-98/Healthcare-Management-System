package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	@Query(value = "Select * from Patient where phoneno = ?1",nativeQuery = true)
	Optional<Patient> findPatientByPhoneNumber(Long phoneno);
}
