package com.makogon.tutor.repository;

import com.makogon.tutor.model.LessonStudent;
import com.makogon.tutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonStudentRepository extends JpaRepository<LessonStudent, Long> {
    List<LessonStudent> findAll();

    List<LessonStudent> findAllByStudentStudentId(long studentId);

    LessonStudent findByLessonStudentId(Long lessonStudentId);

    List<LessonStudent> findAllByaClassTutor(Tutor tutor);
}