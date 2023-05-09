package com.example.donaresange.repo;

import com.example.donaresange.model.Doctor;
import com.example.donaresange.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
    Iterable<Doctor> findAllByLocationId(Integer locationId);
    Optional<Doctor> findByUserId(Integer doctorId);
}
