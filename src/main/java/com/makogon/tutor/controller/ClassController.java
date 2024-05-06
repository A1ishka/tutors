package com.makogon.tutor.controller;
import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.*;
import com.makogon.tutor.model.Class;
import com.makogon.tutor.service.ClassService;
import com.makogon.tutor.service.StudentService;
import com.makogon.tutor.service.TutorService;
import com.makogon.tutor.service.UserService;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller("ClassController")
@RequiredArgsConstructor
public class ClassController {

    private final StudentService studentService;
    private final TutorService tutorService;
    private final UserService userService;
    private final ClassService classService;
    private final AuthorityController authorityController;

    @GetMapping("/classes")
    public String classesForStudents(Model model, Principal principal) {
        model.addAttribute("classes", classService.getClasses());
        model.addAttribute("user", authorityController.user);
//        String username = principal.getName();
        User user = userService.getUserByPrincipal(principal);
        Student student = studentService.getStudentByUserId(user.getUserId());
        List<LessonStudent> lessonStudents = null;
        try {
            lessonStudents = studentService.getLessonsForStudent(student.getStudentId());
        } catch (ChangeSetPersister.NotFoundException e) {
            System.out.println(e);
        }
        model.addAttribute("lessonStudents", lessonStudents);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "classes-student";
    }
    @GetMapping("/classes-tutor")
    public String classesForTutors(Model model, Principal principal) {
//        model.addAttribute("classes", classService.getClasses());
//        model.addAttribute("user", authorityController.user);
////        String username = principal.getName();
//        User user = userService.getUserByPrincipal(principal);
//        Tutor tutor = tutorService.findByUserUserId(user.getUserId());
//        List<Class> classes = classService.getClassesForTutor(tutor);
//
//        model.addAttribute("classes", classes);
////        model.addAttribute("administrator", Role.ADMINISTRATOR);

        User user = userService.getUserByPrincipal(principal);
        Tutor tutor = tutorService.findByUserUserId(user.getUserId());
        List<LessonStudent> lessonStudents = studentService.getClassesForTutor(tutor);
        model.addAttribute("lessonStudents", lessonStudents);

        return "classes-tutor";
    }

    @GetMapping("/classes-admin")
    public String classesForAdmin(Model model) {
        List<LessonStudent> lessonStudents = studentService.getLessonStudents();
        model.addAttribute("lessonStudents", lessonStudents);
        return "classes-admin";
    }
    @PostMapping("/class/delete/{classId}")
    public String deleteClass(@PathVariable String classId) {
        long longId= IdToLong.convert(classId);
        classService.deleteClass(longId);
        return "redirect:/class";
    }

    @GetMapping("/class/create")
    public String createClass(Model model) {
        model.addAttribute("class8", new Class());
        return "class-create";
    }

    @PostMapping("/class/add")
    public String addClass(@ModelAttribute Class class8) {
        Class classToSave = new Class();
        classToSave.setTutor(class8.getTutor());
        classToSave.setPrice(class8.getPrice());
        classToSave.setSpecialization(class8.getSpecialization());
        classToSave.setOrganization(class8.getOrganization());
        classToSave.setDurationMinutes(class8.getDurationMinutes());
        classService.saveClass(classToSave);
        return "redirect:/success";
    }
//    @PostMapping("/class/add")
//    public String addClass(@ModelAttribute("class8") Class class8) {
//        classService.saveClass(class8);
//        return "redirect:/class";
//    }

    @PostMapping("/class/edit/{classId}")
    public String editClass(@PathVariable String classId, Class class8) {
        long longId= IdToLong.convert(classId);
        classService.editClass(longId, class8);
        return "redirect:/success";
    }

    @GetMapping("/class/{classId}")
    public String showClass(@PathVariable String classId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId= IdToLong.convert(classId);
        model.addAttribute("class", classService.getClass(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "class-info";
    }

}
