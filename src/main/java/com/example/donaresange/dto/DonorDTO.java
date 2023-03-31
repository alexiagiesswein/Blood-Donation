package com.example.donaresange.dto;

import com.example.donaresange.model.Donor;
import com.example.donaresange.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonorDTO {
    private User user;
    private Donor donor;
}
