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
//@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long managerId;

    private String fullName;
    @ManyToOne
    private Organization organization;

    @OneToOne
//    @JoinColumn(name = "user_id")
    private User user;
}
