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
//@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;

    private String courseName;
    private String description;

    @ManyToOne
//    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne
//    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
//    @JoinColumn(name = "duration_id")
    private Duration duration;

    private double price;

    @ManyToOne
//    @JoinColumn(name = "organization_id")
    private Organization organization;
}
