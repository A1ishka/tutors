package com.makogon.tutor.repository;

import com.makogon.tutor.model.Course;
import com.makogon.tutor.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findAllByOrganization(Organization organization);
    Course findByCourseId(long courseId);
}