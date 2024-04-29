package com.makogon.tutor.controller;

import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.Organization;
import com.makogon.tutor.model.Specialization;
import com.makogon.tutor.service.OrganisationService;
import com.makogon.tutor.service.SpecializationService;
import com.makogon.tutor.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("SpecializationController")
@RequiredArgsConstructor
public class SpecializationController {

    private final TutorService tutorService;
    private final SpecializationService specializationService;
    private final AuthorityController authorityController;

    @GetMapping("/specializations")
    public String showSpecializations(Model model) {
        model.addAttribute("tutors", tutorService.getTutors());
        model.addAttribute("specializationTutors", tutorService.getSpecializationTutors());
        model.addAttribute("specializations", specializationService.getSpecializations());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "specializations";
    }

    @PostMapping("/specialization/delete/{specializationId}")
    public String deleteSpecialization(@PathVariable String specializationId) {
        long longId = IdToLong.convert(specializationId);
        specializationService.deleteSpecialization(longId);
        return "redirect:/specialization";
    }

    @GetMapping("/specialization/create")
    public String createSpecialization() {
        return "specialization-create";
    }

    @PostMapping("/specialization/add")
    public String addSpecialization(Specialization specialization) {
        specializationService.saveSpecialization(specialization);
        return "redirect:/specialization";
    }

    @PostMapping("/specialization/edit/{specializationId}")
    public String editSpecialization(@PathVariable String specializationId, Specialization specialization) {
        long longId = IdToLong.convert(specializationId);
        specializationService.editSpecialization(longId, specialization);
        return "redirect:/specialization";
    }

    @GetMapping("/specialization/{specializationId}")
    public String showSpecialization(@PathVariable String specializationId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(specializationId);
        model.addAttribute("specialization", specializationService.getSpecialization(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "specialization-info";
    }

}

