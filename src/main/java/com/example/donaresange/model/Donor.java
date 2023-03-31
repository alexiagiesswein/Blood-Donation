package com.example.donaresange.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name="donor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "user_id")
    private Integer userId;

}