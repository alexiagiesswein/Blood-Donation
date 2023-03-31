package com.example.donaresange.service;

import com.example.donaresange.dto.DoctorDTO;
import com.example.donaresange.model.Doctor;
import com.example.donaresange.model.Donor;
import com.example.donaresange.model.Location;
import com.example.donaresange.model.User;
import com.example.donaresange.repo.DoctorRepo;
import com.example.donaresange.repo.LocationRepo;
import com.example.donaresange.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {
    @Autowired
    private final UserRepo userRepo;
    private final DoctorRepo doctorRepo;
    private final LocationRepo locationRepo;

    public Boolean add(String email, String password, String name, Integer locationId, Integer shiftStart, Integer shiftEnd){
        if(email.length()==0 || password.length()==0 || name.length()==0)
            throw new RuntimeException("Invalid input!");
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isPresent())
            throw new RuntimeException("User already exists!");
        Optional<Location> optionalLocation = locationRepo.findById(locationId);
        if(optionalLocation.isEmpty())
            throw new RuntimeException("Location doesn't exist!");
        User user = new User(0, email, password, name, "doctor");
        userRepo.save(user);
        user = userRepo.findByEmail(email).get();
        Doctor doctor = new Doctor(0, user.getId(), locationId, shiftStart, shiftEnd);
        doctorRepo.save(doctor);
        return true;
    }

    public List<DoctorDTO> getAll(){
        Iterable<Doctor> doctors = doctorRepo.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor doctor : doctors){
            doctorDTOS.add(new DoctorDTO(userRepo.findById(doctor.getUserId()).get(), doctor));
        }
        return doctorDTOS;
    }

    public Boolean edit(Integer doctorId, String email, String password, String name, Integer locationId, Integer shiftStart, Integer shiftEnd){
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);
        if(optionalDoctor.isEmpty())
            throw new RuntimeException("Doctor doesn't exist!");
        Doctor doctor = optionalDoctor.get();
        User user = userRepo.findById(doctor.getUserId()).get();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        doctor.setLocationId(locationId);
        doctor.setShiftStart(shiftStart);
        doctor.setShiftEnd(shiftEnd);

        userRepo.save(user);
        doctorRepo.save(doctor);
        return true;
    }

    public Boolean delete(Integer doctorId){
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);
        if(optionalDoctor.isEmpty())
            return false;
        User user = userRepo.findById(optionalDoctor.get().getUserId()).get();
        userRepo.delete(user);
        doctorRepo.delete(optionalDoctor.get());
        return true;
    }
}