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
//@Table(name = "organization_tutors")
public class OrganizationTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long organizationTutorId;

    @ManyToOne
//    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
//    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    private String startDate;
}
