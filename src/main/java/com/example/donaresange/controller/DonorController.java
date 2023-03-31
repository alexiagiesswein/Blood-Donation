package com.example.donaresange.controller;

import com.example.donaresange.dto.DonorDTO;
import com.example.donaresange.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DonorController {

    @Autowired
    DonorService donorService;

    @PutMapping("/donor/edit")
    public Boolean edit(@RequestParam() Integer donorId,@RequestParam() String password,@RequestParam() String name,@RequestParam() String address,@RequestParam() String bloodType) {
        return donorService.edit(donorId, password, name, address, bloodType);
    }

    @DeleteMapping("/donor/delete")
    public Boolean delete(@RequestParam() Integer donorId) {
        return donorService.delete(donorId);
    }

    @GetMapping("/donor/get")
    public DonorDTO get(@RequestParam() Integer donorId) {
        return donorService.get(donorId);
    }
}