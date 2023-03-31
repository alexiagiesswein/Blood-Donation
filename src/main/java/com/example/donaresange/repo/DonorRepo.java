package com.example.donaresange.repo;

import com.example.donaresange.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends JpaRepository<Donor,Integer> {
}
