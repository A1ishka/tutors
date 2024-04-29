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
//@Table(name = "course_students")
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseStudentId;

    @ManyToOne
//    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
//    @JoinColumn(name = "student_id")
    private Student student;

    private String courseStatus;
}
