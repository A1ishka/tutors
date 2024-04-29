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
//@Table(name = "lesson_students")
public class LessonStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lessonStudentId;

    @ManyToOne
//    @JoinColumn(name = "lesson_id")
    private Class aClass;

    @ManyToOne
//    @JoinColumn(name = "student_id")
    private Student student;

    private String lessonStatus;
}
