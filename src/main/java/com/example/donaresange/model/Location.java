package com.example.donaresange.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name="location")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

}
