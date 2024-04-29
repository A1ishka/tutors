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
//@Table(name = "specialization_tutors")
public class SpecializationTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long specializationTutorId;

    @ManyToOne
//    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
//    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
