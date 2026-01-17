package com.entity;

public record Appointment(int aid,String pid,String patientName,String did,
String doctorName,float amount,String timeslot) {}
