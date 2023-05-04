package com.example.donaresange.repo;

import com.example.donaresange.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    Iterable<Appointment> findAllByDonorId(Integer donorId);
    Iterable<Appointment> findAllByDoctorId(Integer doctorId);
    Iterable<Appointment> findAllByDonorIdAndDoctorId(Integer donorId, Integer doctorId);
    Iterable<Appointment> findAllByDateAndDoctorId(Date date, Integer doctorId);
    Iterable<Appointment> findAllByDateAndDoctorIdAndDonatedMl(Date date, Integer doctorId, Integer donatedMl);
}
