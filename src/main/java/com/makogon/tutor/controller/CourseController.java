package com.makogon.tutor.controller;

import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.Course;
import com.makogon.tutor.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("CourseController")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final AuthorityController authorityController;

    @GetMapping("/courses")
    public String showCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "courses";
    }

    @PostMapping("/course/delete/{courseId}")
    public String deleteCourse(@PathVariable String courseId) {
        long longId = IdToLong.convert(courseId);
        courseService.deleteCourse(longId);
        return "redirect:/course";
    }

    @GetMapping("/course/create")
    public String createCourse() {
        return "course-create";
    }

    @PostMapping("/course/add")
    public String addCourse(Course course) {
        courseService.saveCourse(course);
        return "redirect:/course";
    }

    @PostMapping("/course/edit/{courseId}")
    public String editCourse(@PathVariable String courseId, Course course) {
        long longId = IdToLong.convert(courseId);
        courseService.editCourse(longId, course);
        return "redirect:/course";
    }

    @GetMapping("/class/{courseId}")
    public String showCourse(@PathVariable String courseId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(courseId);
        model.addAttribute("course", courseService.getCourse(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "class-info";
    }

}
