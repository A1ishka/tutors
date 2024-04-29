package com.makogon.tutor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classId;

    @ManyToOne
//    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
    @ManyToOne
//    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    private int durationMinutes;
    private double price;

    @ManyToOne
//    @JoinColumn(name = "organization_id")
    private Organization organization;
}
