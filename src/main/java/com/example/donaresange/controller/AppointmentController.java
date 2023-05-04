package com.example.donaresange.controller;

import com.example.donaresange.model.Appointment;
import com.example.donaresange.service.AppointmentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/appointment/add")
    public Boolean add(@RequestParam() Integer doctorId, @RequestParam() Integer donorId, @RequestParam() String time) throws ParseException, MessagingException {
        return appointmentService.add(doctorId, donorId, time);
    }

    @GetMapping("/appointments")
    public Iterable<Appointment> get(@RequestParam(defaultValue = "0") Integer donorId, @RequestParam(defaultValue = "0") Integer doctorId){
        return appointmentService.get(donorId, doctorId);
    }

    @GetMapping("/appointment/getHours")
    public List<String> getHours(@RequestParam() Integer doctorId, @RequestParam() Date date){
        return appointmentService.getHours(doctorId, date);
    }

    @DeleteMapping("/appointment/cancel")
    public Boolean cancelAppointment(@RequestParam() Integer id){
        return appointmentService.cancelAppointment(id);
    }

    @PutMapping("/appointment/confirm")
    public Boolean confirm(@RequestParam() Integer id, @RequestParam(defaultValue = "0") Integer ml) {
        return appointmentService.confirm(id, ml);
    }

    @PutMapping("/appointment/today")
    public Iterable<Appointment> appointmentsByDate(@RequestParam() Integer doctorId, @RequestParam() Date date){
        return  appointmentService.appointmentsByDate(doctorId, date);
    }
}