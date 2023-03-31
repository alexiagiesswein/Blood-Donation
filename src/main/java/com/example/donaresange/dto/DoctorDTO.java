package com.example.donaresange.dto;

import com.example.donaresange.model.Doctor;
import com.example.donaresange.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDTO {
    private User user;
    private Doctor doctor;
}
