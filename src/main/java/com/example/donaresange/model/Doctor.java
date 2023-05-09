package com.example.donaresange.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name="doctor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Doctor {
    public Doctor(Integer id, Integer userId, Integer locationId, Integer shiftStart, Integer shiftEnd) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "shift_start")
    private Integer shiftStart;

    @Column(name = "shift_end")
    private Integer shiftEnd;

    @Column(name = "location")
    private String location;
}
