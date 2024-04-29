package com.makogon.tutor.service;

import com.makogon.tutor.model.Course;
import com.makogon.tutor.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseService")
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(long classId) {
        courseRepository.deleteById(classId);
    }

    public Course getCourse(long courseId) throws ChangeSetPersister.NotFoundException {
        return courseRepository.findByCourseId(courseId);
    }

    public void editCourse(long courseId, Course course) {
        course.setCourseId(courseId);
        courseRepository.save(course);
    }
}