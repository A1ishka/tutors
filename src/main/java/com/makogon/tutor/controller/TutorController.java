package com.makogon.tutor.controller;

import com.google.gson.Gson;
import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.*;
import com.makogon.tutor.model.Class;
import com.makogon.tutor.service.OrganisationService;
import com.makogon.tutor.service.SpecializationService;
import com.makogon.tutor.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*")
//@RequestMapping("tutors")

@Controller("TutorController")
@RequiredArgsConstructor
public class TutorController {

    private final OrganisationService organisationService;
    private final SpecializationService specializationService;
    private final TutorService tutorService;
    private final AuthorityController authorityController;

    @GetMapping("/search-tutors")
    @ResponseBody
    public String searchTutors(@RequestParam("query") String query) {
        List<Tutor> tutors = tutorService.searchTutors(query);
        Gson gson = new Gson();
        return gson.toJson(tutors);
    }

    @GetMapping("/tutors")
    public String showTutor(Model model) {
        model.addAttribute("tutors", tutorService.getTutors());
//        model.addAttribute("specializationTutors", tutorService.getSpecializationTutors());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "tutors";
    }
//    @GetMapping("/tutors")
//    public List<Tutor> showTutor() {
//        return tutorService.getTutors();
//    }

    @PostMapping("/tutor/delete/{tutorId}")
    public String deleteTutor(@PathVariable String tutorId) {
        long longId = IdToLong.convert(tutorId);
        tutorService.deleteTutor(longId);
        return "redirect:/tutors";
    }

    @GetMapping("/tutor/create")
    public String createTutor() {
        return "tutor-create";
    }

    @PostMapping("/tutor/add")
    public String addTutor(Tutor tutor) {
        tutorService.saveTutor(tutor);
        return "redirect:/success";
    }

    @PostMapping("/tutor/edit/{tutorId}")
    public String editTutor(@PathVariable String tutorId, Tutor tutor) {
        long longId = IdToLong.convert(tutorId);
        tutorService.editTutor(longId, tutor);
        return "redirect:/success";
    }

    @GetMapping("/tutor/{tutorId}")
    public String showTutor(@PathVariable String tutorId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(tutorId);
        Tutor tutor = tutorService.getTutor(longId);
        model.addAttribute("tutor", tutor);
        model.addAttribute("specializationTutors", tutorService.findAllByTutor(tutor));
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        Class class8 = new Class();
        class8.setTutor(tutor);
        class8.setSpecialization(specializationService.getSpecialization(1));
        model.addAttribute("class8", class8);
        return "tutor-info";
    }


    @GetMapping("/specializationTutors")
    public String showSpecializationTutor(Model model) {
        model.addAttribute("tutors", tutorService.getTutors());
        model.addAttribute("specializationTutors", tutorService.getSpecializationTutors());
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "specialization-tutors";
    }

    @PostMapping("/specializationTutor/delete/{specializationTutorId}")
    public String deleteSpecializationTutor(@PathVariable String specializationTutorId) {
        long longId = IdToLong.convert(specializationTutorId);
        tutorService.deleteSpecializationTutor(longId);
        return "redirect:/specializationtutor";
    }

    @GetMapping("/specializationTutor/create")
    public String createSpecializationTutor() {
        return "specialization-tutor-create";
    }

    @PostMapping("/specializationTutor/add")
    public String addSpecializationTutor(SpecializationTutor specializationTutor) {
        tutorService.saveSpecializationTutor(specializationTutor);
        return "redirect:/specializationTutor";
    }
    @PostMapping("/specializationTutor/edit/{specializationTutorId}")
    public String editSpecializationTutor(@PathVariable String specializationTutorId, SpecializationTutor specializationTutor) {
        long longId = IdToLong.convert(specializationTutorId);
        tutorService.editSpecializationTutor(longId, specializationTutor);
        return "redirect:/specializationTutor";
    }
    @GetMapping("/specializationTutor/{specializationTutorId}")
    public String showSpecializationTutor(@PathVariable String specializationTutorId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(specializationTutorId);
        model.addAttribute("specializationTutor", tutorService.getSpecializationTutor(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "specializationTutor-info";
    }
    @GetMapping("/organization-tutors/{organizationId}")
    public String showOrganizationTutor(@PathVariable long organizationId, Model model) throws ChangeSetPersister.NotFoundException {
//        model.addAttribute("organization_tutors", tutorService.getOrganizationTutors());
        Organization organization = organisationService.getOrganization(organizationId);
        model.addAttribute("organization_tutors", tutorService.getOrganizationTutorsByOrganization(organization));
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organization-tutors";
    }
    @GetMapping("/organization-tutors-for-admin")
    public String showOrganizationTutorForAdmin(Model model) {
        model.addAttribute("organization_tutors", tutorService.getOrganizationTutors());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organization-tutors";
    }
    @PostMapping("/organizationTutor/delete/{organizationTutorId}")
    public String deleteOrganizationTutor(@PathVariable String organizationTutorId) {
        long longId = IdToLong.convert(organizationTutorId);
        tutorService.deleteOrganizationTutor(longId);
        return "redirect:/success";
    }

    @GetMapping("/organizationTutor/create")
    public String createOrganizationTutor() {
        return "organizationTutor-create";
    }

    @PostMapping("/organizationTutor/add")
    public String addOrganizationTutor(OrganizationTutor organizationTutor) {
        tutorService.saveOrganizationTutor(organizationTutor);
        return "redirect:/organizationTutor";
    }

    @PostMapping("/organizationTutor/edit/{organizationTutorId}")
    public String editOrganizationTutor(@PathVariable String organizationTutorId, OrganizationTutor organizationTutor) {
        long longId = IdToLong.convert(organizationTutorId);
        tutorService.editOrganizationTutor(longId, organizationTutor);
        return "redirect:/organizationTutor";
    }

    @GetMapping("/organizationTutor/{organizationTutorId}")
    public String showOrganizationTutor(@PathVariable String organizationTutorId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(organizationTutorId);
        model.addAttribute("organizationTutor", tutorService.getOrganizationTutor(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organization-tutors";
    }

}

