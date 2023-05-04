package com.example.donaresange.service;

import com.example.donaresange.model.Appointment;
import com.example.donaresange.model.Doctor;
import com.example.donaresange.model.Donor;
import com.example.donaresange.model.User;
import com.example.donaresange.repo.AppointmentRepo;
import com.example.donaresange.repo.DoctorRepo;
import com.example.donaresange.repo.DonorRepo;
import com.example.donaresange.repo.UserRepo;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    @Autowired
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final DonorRepo donorRepo;

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    EmailSenderService emailSenderService;


    public Boolean add(Integer doctorId, Integer donorId, String time) throws ParseException, MessagingException {
        java.util.Date date = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(time);
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);
        if (optionalDoctor.isEmpty())
            throw new RuntimeException("Doctor doesn't exist");
        Optional<Donor> optionalDonor = donorRepo.findById(donorId);
        if (optionalDonor.isEmpty())
            throw new RuntimeException("Donor doesn't exist");
        Doctor doctor = optionalDoctor.get();
        if (date.getHours() < doctor.getShiftStart() || date.getHours() >= doctor.getShiftEnd())
            throw new RuntimeException("Appointment outside doctor's shift");
        int timeMinutes = date.getHours() * 60 + date.getMinutes();
        Appointment appointment = new Appointment(0, doctorId, donorId, new Date(date.getTime()), timeMinutes, 0, optionalDonor.get().getBloodType());
        appointmentRepo.save(appointment);

        User user = userRepo.findById(donorId).get();

        emailSenderService.sendMailWithAttachment(user.getEmail(),
                "You have made an appointment!" ,
                "Confirm appointment");

        return true;
    }

    public Iterable<Appointment> get(Integer donorId, Integer doctorId) {
        if(donorId != 0 && doctorId != 0)
            return appointmentRepo.findAllByDonorIdAndDoctorId(donorId, doctorId);
        else if(donorId != 0)
            return appointmentRepo.findAllByDonorId(donorId);
        else if(doctorId != 0)
            return appointmentRepo.findAllByDoctorId(doctorId);
        return null;
    }

    public List<String> getHours(Integer doctorId, Date date) {
        Iterable<Appointment> appointments = appointmentRepo.findAllByDateAndDoctorId(date, doctorId);
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);
        if (optionalDoctor.isEmpty())
            throw new RuntimeException("Doctor doesn't exist!");
        Doctor doctor = optionalDoctor.get();
        List<Integer> minutes = new ArrayList<>();
        for (int i = doctor.getShiftStart() * 60; i < doctor.getShiftEnd() * 60; i += 30) {
            minutes.add(i);
        }

        for (Appointment appointment : appointments) {
            for (int i = 0; i < minutes.size(); i++) {
                if(minutes.get(i).equals(appointment.getTimeMinutes())){
                    minutes.remove(i);
                    break;
                }
            }
        }

        List<String> minutesString = new ArrayList<>();
        for(Integer minute:minutes){
            if(minute%60 == 0)
                minutesString.add("" + (minute/60) + ":00");
            else
                minutesString.add("" + (minute/60) + ":" + (minute%60));
        }

        return  minutesString;
    }

    public Boolean cancelAppointment(Integer id){
        Optional<Appointment> optional = appointmentRepo.findById(id);
        if(optional.isEmpty() || optional.get().getDonatedMl()!=0)
            return false;
        appointmentRepo.delete(optional.get());
        return true;
    }

    public Boolean confirm(Integer id, Integer ml){
        Optional<Appointment> optional = appointmentRepo.findById(id);
        if(optional.isEmpty() || optional.get().getDonatedMl()!=0)
            return false;
        Appointment appointment = optional.get();
        appointment.setDonatedMl(ml);
        appointmentRepo.save(appointment);
        return true;
    }

    public Iterable<Appointment> appointmentsByDate(Integer doctorId, Date date){
        return appointmentRepo.findAllByDateAndDoctorIdAndDonatedMl(date, doctorId, 0);
    }
}