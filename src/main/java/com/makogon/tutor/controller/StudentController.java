package com.makogon.tutor.controller;

import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.CourseStudent;
import com.makogon.tutor.model.LessonStudent;
import com.makogon.tutor.model.Student;
import com.makogon.tutor.service.CourseService;
import com.makogon.tutor.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("StudentController")
@RequiredArgsConstructor
public class StudentController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final AuthorityController authorityController;

    //for students

    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "students";
    }

    @PostMapping("/student/delete/{studentId}")
    public String deleteStudent(@PathVariable String studentId) {
        long longId = IdToLong.convert(studentId);
        studentService.deleteStudent(longId);
        return "redirect:/student";
    }

    @GetMapping("/student/create")
    public String createStudent() {
        return "student-create";
    }

    @PostMapping("/student/add")
    public String addStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/student";
    }

    @PostMapping("/student/edit/{studentId}")
    public String editStudent(@PathVariable String studentId, Student student) {
        long longId = IdToLong.convert(studentId);
        studentService.editStudent(longId, student);
        return "redirect:/student";
    }

    @GetMapping("/student/{studentId}")
    public String showStudent(@PathVariable String studentId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(studentId);
        model.addAttribute("student", studentService.getStudent(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "student-info";
    }


    //for lessons of student

    @GetMapping("/lessonStudent")
    public String showLessonStudents(Model model) {
        model.addAttribute("lessonStudents", studentService.getLessonStudents());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "lesson-students";
    }

    @PostMapping("/lessonStudent/delete/{lessonStudentId}")
    public String deleteLessonStudent(@PathVariable String lessonStudentId) {
        long longId = IdToLong.convert(lessonStudentId);
        studentService.deleteLessonStudent(longId);
        return "redirect:/lessonStudent";
    }

    @PostMapping("/lessonStudent/add")
    public String addLessonStudent(LessonStudent lessonStudent) {
        studentService.saveLessonStudent(lessonStudent);
        return "redirect:/lessonStudent";
    }

    @PostMapping("/lessonStudent/edit/{lessonStudentId}")
    public String editLessonStudent(@PathVariable String lessonStudentId, LessonStudent lessonStudent) {
        long longId = IdToLong.convert(lessonStudentId);
        studentService.editLessonStudent(longId, lessonStudent);
        return "redirect:/lessonStudent";
    }

//    @GetMapping("/lessonStudent/{lessonStudentId}")
//    public String showLessonStuden(@PathVariable String lessonStudentId, Model model) throws ChangeSetPersister.NotFoundException {
//        long longId = IdToLong.convert(lessonStudentId);
//        model.addAttribute("lessonStudent", studentService.getLessonStudent(longId));
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
//        return "lessonStudent-info";
//    }

    //for courses of student

    @GetMapping("/course-students")
    public String showCourseStudents(Model model) {
        model.addAttribute("courseStudent", studentService.getCourseStudents());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "success";
    }
    @GetMapping("/courses/{studentId}")
    public String showCoursesForStudent(@PathVariable String studentId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(studentId);
        model.addAttribute("courseStudents", studentService.getCoursesForStudent(longId));
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "courses-student";
    }
    @PostMapping("/course-students/delete/{courseStudentId}")
    public String deleteCourseStudent(@PathVariable String courseStudentId) {
        long longId = IdToLong.convert(courseStudentId);
        studentService.deleteCourseStudent(longId);
        return "redirect:/course-students";
    }

    @PostMapping("/course-students/add/{courseId}/{userId}")
    public String addCourseStudent(@PathVariable("courseId") long courseId, @PathVariable("userId") long userId, Model model) {

        try {
            CourseStudent courseStudent = new CourseStudent();
            courseStudent.setCourse(courseService.getCourse(courseId));
            courseStudent.setStudent(studentService.getStudentByUserId(userId));
            courseStudent.setCourseStatus("Зарегистрирован");
            studentService.saveCourseStudent(courseStudent);
            model.addAttribute("successMessage", "Курс успешно добавлен!");
        } catch (ChangeSetPersister.NotFoundException exception) {
            model.addAttribute("successMessage", "Курс не добавлен! Что-то пошло не так...");
        }

        return "redirect:/course-students";
    }

    @PostMapping("/course-students/edit/{courseStudentId}")
    public String editCourseStudent(@PathVariable String courseStudentId, CourseStudent courseStudent) {
        long longId = IdToLong.convert(courseStudentId);
        studentService.editCourseStudent(longId, courseStudent);
        return "redirect:/course-students";
    }

//    @GetMapping("/courseStudent/{courseStudentId}")
//    public String showCourseStudent(@PathVariable String courseStudentId, Model model) throws ChangeSetPersister.NotFoundException {
//        long longId = IdToLong.convert(courseStudentId);
//        model.addAttribute("courseStudent", studentService.getCourseStudent(longId));
//        model.addAttribute("user", authorityController.user);
////        model.addAttribute("administrator", Role.ADMINISTRATOR);
//        model.addAttribute("manager", Role.MANAGER);
//
//        return "courseStudent-info";
//    }
}
