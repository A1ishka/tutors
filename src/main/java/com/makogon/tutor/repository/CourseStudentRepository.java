package com.makogon.tutor.repository;

import com.makogon.tutor.model.Course;
import com.makogon.tutor.model.CourseStudent;
import com.makogon.tutor.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
    @Override
    List<CourseStudent> findAll();

    CourseStudent findByCourseStudentId(long courseStudentId);
    List<CourseStudent> findAllByStudentStudentId(long studentId);
    List<CourseStudent> findAllByCourseCourseId(long courseId);
}