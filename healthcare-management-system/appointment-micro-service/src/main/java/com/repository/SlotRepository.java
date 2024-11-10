package com.repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Slot;

import jakarta.transaction.Transactional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer>{

	@Query("SELECT s FROM Slot s WHERE s.time = :time")
    public Slot getSlotByTime(@Param("time") LocalTime newtimeslot);
	
	@Modifying
	@Transactional
	@Query("UPDATE Slot s SET s.bookFlag = :bookFlag WHERE s.sid = :sid and s.did=:did")
    public void updateSlotDetails(@Param("bookFlag") boolean bookFlag, @Param("sid") int sid, @Param("did") int did);
	
	
	@Query("SELECT s.time FROM Slot s WHERE s.did = :did and s.bookFlag = false")
    public LocalTime[] findSlotsByDoctorId(@Param("did") int did);
}
