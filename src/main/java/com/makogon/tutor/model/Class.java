package com.makogon.tutor.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Setter
    @ManyToOne
//    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    private int durationMinutes;
    private double price;

    @ManyToOne
//    @JoinColumn(name = "organization_id")
    private Organization organization;
}
