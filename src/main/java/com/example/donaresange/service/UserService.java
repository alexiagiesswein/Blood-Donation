package com.example.donaresange.service;

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
public class UserService {
    @Autowired
    private final UserRepo userRepo;
    private final DonorRepo donorRepo;

    public Boolean register(String email, String password, String confirmPassword, String name, String bloodType, String address){
        if(email.length()==0 || password.length()==0 || name.length()==0 || bloodType.length()==0 || address.length()==0)
            throw new RuntimeException("Invalid input!");
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isPresent())
            throw new RuntimeException("User already exists!");
        if(confirmPassword.compareTo(password)!=0)
            throw new RuntimeException("Password not the same!");
        User user = new User(0, email, password, name, "donor");
        userRepo.save(user);
        user = userRepo.findByEmail(email).get();
        Donor donor = new Donor(0, address, bloodType, user.getId());
        donorRepo.save(donor);
        return true;
    }

    public User login(String email, String password){
        if(email.length()==0||password.length()==0)
            throw new RuntimeException("Invalid input!");
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isEmpty())
            throw new RuntimeException("User doesn't exist!");
        User user = optionalUser.get();
        if(user.getPassword().compareTo(password)!=0)
            throw new RuntimeException("Invalid password!");
        return user;
    }
}