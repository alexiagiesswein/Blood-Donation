package com.example.donaresange.service;

import com.example.donaresange.dto.DoctorDTO;
import com.example.donaresange.model.Doctor;
import com.example.donaresange.model.Location;
import com.example.donaresange.repo.LocationRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {
    @Autowired
    private final LocationRepo locationRepo;

    public Iterable<Location> getAll(){
        Iterable<Location> locations = locationRepo.findAll();
        return locations;
    }
}