package com.example.donaresange.controller;

import com.example.donaresange.dto.DoctorDTO;
import com.example.donaresange.model.Doctor;
import com.example.donaresange.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/doctor/add")
    public Boolean add(@RequestParam() String email, @RequestParam() String password, @RequestParam() String name, @RequestParam() Integer locationId, @RequestParam() Integer shiftStart, @RequestParam() Integer shiftEnd) {
        return doctorService.add(email, password, name, locationId, shiftStart, shiftEnd);
    }

    @GetMapping("/doctors")
    public List<DoctorDTO> getAll() {
        return doctorService.getAll();
    }

    @GetMapping("/doctor")
    public Iterable<DoctorDTO> getByLocation(@RequestParam(defaultValue = "0") Integer locationId) {
        return doctorService.getByLocation(locationId);
    }

    @PutMapping("/doctor/edit")
    public Boolean edit(@RequestParam() Integer id, @RequestParam() String email, String password, @RequestParam() String name, @RequestParam() Integer locationId, @RequestParam() Integer shiftStart, @RequestParam() Integer shiftEnd) {
        return doctorService.edit(id, email, password, name, locationId, shiftStart, shiftEnd);
    }

    @DeleteMapping("/doctor/delete")
    public Boolean delete(@RequestParam() Integer doctorId) {
        return doctorService.delete(doctorId);
    }
}