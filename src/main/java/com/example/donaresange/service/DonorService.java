package com.example.donaresange.service;

import com.example.donaresange.dto.DonorDTO;
import com.example.donaresange.model.Donor;
import com.example.donaresange.model.User;
import com.example.donaresange.repo.DonorRepo;
import com.example.donaresange.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonorService {
    @Autowired
    private final UserRepo userRepo;
    private final DonorRepo donorRepo;

    public Boolean edit(Integer donorId, String password, String name, String address, String bloodType){
        Optional<Donor> optionalDonor = donorRepo.findById(donorId);
        if(optionalDonor.isEmpty())
            throw new RuntimeException("Donor doesn't exist!");
        Donor donor = optionalDonor.get();
        User user = userRepo.findById(donor.getUserId()).get();

        user.setPassword(password);
        user.setName(name);
        donor.setAddress(address);
        donor.setBloodType(bloodType);

        userRepo.save(user);
        donorRepo.save(donor);
        return true;
    }

    public Boolean delete(Integer donorId){
        Optional<Donor> optionalDonor = donorRepo.findById(donorId);
        if(optionalDonor.isEmpty())
            return false;
        User user = userRepo.findById(optionalDonor.get().getUserId()).get();
        userRepo.delete(user);
        donorRepo.delete(optionalDonor.get());
        return true;
    }

    public DonorDTO get(Integer donorId){
        Optional<Donor> optionalDonor = donorRepo.findByUserId(donorId);
        if(optionalDonor.isEmpty())
            throw new RuntimeException("Donor doesn't exist!");
        User user = userRepo.findById(optionalDonor.get().getUserId()).get();
        return new DonorDTO(user, optionalDonor.get());
    }
}