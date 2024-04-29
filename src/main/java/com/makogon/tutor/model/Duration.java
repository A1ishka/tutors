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
//@Table(name = "durations")
public class Duration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long durationId;

    private String type;
    private String duration;
}
