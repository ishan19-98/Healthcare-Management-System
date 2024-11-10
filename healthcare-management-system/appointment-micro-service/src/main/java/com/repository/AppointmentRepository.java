package com.repository;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Appointment;

import jakarta.transaction.Transactional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE Appointment a SET a.timeslot = :timeslot WHERE a.aid = :aid")
    public void updateAppointmentTime(@Param("timeslot") String newtimeslot, @Param("aid") int aid);
	
	@Query("Select a.timeslot from Appointment a WHERE a.aid = :id")
	public String getSlotIdById(@Param("id") int id);

}
