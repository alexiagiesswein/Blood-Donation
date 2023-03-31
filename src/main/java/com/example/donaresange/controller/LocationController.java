package com.example.donaresange.controller;

import com.example.donaresange.model.Location;
import com.example.donaresange.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/locations")
    public Iterable<Location> getAll() {
        return locationService.getAll();
    }
}