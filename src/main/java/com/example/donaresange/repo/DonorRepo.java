package com.example.donaresange.repo;

import com.example.donaresange.model.Donor;
import com.example.donaresange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepo extends JpaRepository<Donor,Integer> {
    Optional<Donor> findByUserId(Integer donorId);
}
