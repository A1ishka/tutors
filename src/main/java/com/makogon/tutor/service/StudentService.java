package com.makogon.tutor.service;

import com.makogon.tutor.model.*;
import com.makogon.tutor.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentService")
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final StudentRepository studentRepository;
    private final LessonStudentRepository lessonStudentRepository;

    //for student
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }
    public Student getStudent(long studentId) throws ChangeSetPersister.NotFoundException {
        return studentRepository.findByStudentId(studentId);
    }
    public void editStudent(long studentId, Student student) {
        student.setStudentId(studentId);
        studentRepository.save(student);
    }

    //for lessons of student
    public List<LessonStudent> getLessonStudents() {
        return lessonStudentRepository.findAll();
    }
    public void saveLessonStudent(LessonStudent lessonStudent) {
        lessonStudentRepository.save(lessonStudent);
    }
    public void deleteLessonStudent(long lessonStudentId) {
        lessonStudentRepository.deleteById(lessonStudentId);
    }
    public LessonStudent getLessonStudent(long lessonStudentId) throws ChangeSetPersister.NotFoundException {
        return lessonStudentRepository.findByLessonStudentId(lessonStudentId);
    }
    public List<LessonStudent> getLessonsForStudent(long studentId) throws ChangeSetPersister.NotFoundException {
        return lessonStudentRepository.findAllByStudentStudentId(studentId);
    }
    public void editLessonStudent(long lessonStudentId, LessonStudent lessonStudent) {
        lessonStudent.setLessonStudentId(lessonStudentId);
        lessonStudentRepository.save(lessonStudent);
    }

    //for courses of student
    public List<CourseStudent> getCourseStudents() {
        return courseStudentRepository.findAll();
    }
    public void saveCourseStudent(CourseStudent courseStudent) {
        courseStudentRepository.save(courseStudent);
    }
    public void deleteCourseStudent(long courseStudentId) {
        courseStudentRepository.deleteById(courseStudentId);
    }
    public CourseStudent getCourseStudent(long courseStudentId) throws ChangeSetPersister.NotFoundException {
        return courseStudentRepository.findByCourseStudentId(courseStudentId);
    }
    public List<CourseStudent> getStudentsForCourse(long courseId) throws ChangeSetPersister.NotFoundException {
        return courseStudentRepository.findAllByCourseCourseId(courseId);
    }
    public List<CourseStudent> getCoursesForStudent(long studentId) throws ChangeSetPersister.NotFoundException {
        return courseStudentRepository.findAllByStudentStudentId(studentId);
    }
    public void editCourseStudent(long courseStudentId, CourseStudent courseStudent) {
        courseStudent.setCourseStudentId(courseStudentId);
        courseStudentRepository.save(courseStudent);
    }
}