package com.makogon.tutor.controller;
import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.Class;
import com.makogon.tutor.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("ClassController")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final AuthorityController authorityController;

    @GetMapping("/classes")
    public String classes(Model model) {
        model.addAttribute("classes", classService.getClasses());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "classes";
    }

    @PostMapping("/class/delete/{classId}")
    public String deleteClass(@PathVariable String classId) {
        long longId= IdToLong.convert(classId);
        classService.deleteClass(longId);
        return "redirect:/class";
    }

    @GetMapping("/class/create")
    public String createClass() {
        return "class-create";
    }

    @PostMapping("/class/add")
    public String addClass(Class class8) {
        classService.saveClass(class8);
        return "redirect:/class";
    }

    @PostMapping("/class/edit/{classId}")
    public String editClass(@PathVariable String classId, Class class8) {
        long longId= IdToLong.convert(classId);
        classService.editClass(longId, class8);
        return "redirect:/class";
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
