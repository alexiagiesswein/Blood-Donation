package com.example.donaresange.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name="appointment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "donor_id")
    private Integer donorId;

    @Column(name = "date")
    private Date date;

    @Column(name = "time_minutes")
    private Integer timeMinutes;
}
