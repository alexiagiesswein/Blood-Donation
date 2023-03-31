package com.example.donaresange.service;

import com.example.donaresange.model.Appointment;
import com.example.donaresange.model.Doctor;
import com.example.donaresange.model.Donor;
import com.example.donaresange.repo.AppointmentRepo;
import com.example.donaresange.repo.DoctorRepo;
import com.example.donaresange.repo.DonorRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {
    @Autowired
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final DonorRepo donorRepo;

    public Boolean add(Integer doctorId, Integer donorId, String time) throws ParseException {
        java.util.Date date = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(time);
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);
        if(optionalDoctor.isEmpty())
            throw new RuntimeException("Doctor doesn't exist");
        Optional<Donor> optionalDonor = donorRepo.findById(donorId);
        if(optionalDonor.isEmpty())
            throw new RuntimeException("Donor doesn't exist");
        Doctor doctor = optionalDoctor.get();
       if(date.getHours()<doctor.getShiftStart() || date.getHours() >= doctor.getShiftEnd())
            throw new RuntimeException("Appointment outside doctor's shift");
        int timeMinutes = date.getHours() * 60 + date.getMinutes();
        Appointment appointment = new Appointment(0, doctorId, donorId, new Date(date.getTime()), timeMinutes);
        appointmentRepo.save(appointment);
        return true;
    }

    public Iterable<Appointment> get(Integer donorId){
        return appointmentRepo.findAllByDonorId(donorId);
    }
}