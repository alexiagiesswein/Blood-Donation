package com.example.donaresange.repo;

import com.example.donaresange.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    Iterable<Appointment> findAllByDonorId(Integer donorId);
}
