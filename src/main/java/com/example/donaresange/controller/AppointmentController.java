package com.example.donaresange.controller;

import com.example.donaresange.model.Appointment;
import com.example.donaresange.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/appointment/add")
    public Boolean add(@RequestParam() Integer doctorId, @RequestParam() Integer donorId, @RequestParam() String time) throws ParseException {
        return appointmentService.add(doctorId, donorId, time);
    }

    @GetMapping("/appointments")
    public Iterable<Appointment> get(@RequestParam() Integer donorId){
        return appointmentService.get(donorId);
    }
}