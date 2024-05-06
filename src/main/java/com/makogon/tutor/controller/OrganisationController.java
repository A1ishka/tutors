package com.makogon.tutor.controller;

import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.Organization;
import com.makogon.tutor.service.OrganisationService;
import com.makogon.tutor.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("OrganisationController")
@RequiredArgsConstructor
public class OrganisationController {

    private final TutorService tutorService;
    private final OrganisationService organisationService;
    private final AuthorityController authorityController;

    @GetMapping("/organisations")
    public String organisations(Model model) {
        model.addAttribute("organizations", organisationService.getOrganizations());
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organizations";
    }

    @PostMapping("/organisation/delete/{organisationId}")
    public String deleteOrganisation(@PathVariable String organisationId) {
        long longId = IdToLong.convert(organisationId);
        organisationService.deleteOrganization(longId);
        return "redirect:/success";
    }

    @GetMapping("/organisation/create")
    public String createOrganisation(Model model) {
        model.addAttribute("organisation", new Organization());
        return "organization-create";
    }

    @PostMapping("/organisation/add")
    public String addOrganisation(Organization organisation) {
        organisationService.saveOrganization(organisation);
        return "redirect:/success";
    }

    @PostMapping("/organisation/edit/{organisationId}")
    public String OrganisationEdit(@PathVariable String organisationId, Organization organisation) {
        long longId = IdToLong.convert(organisationId);
        organisationService.editOrganization(longId, organisation);
        return "redirect:/success";
    }

    @GetMapping("/organisation/{organisationId}")
    public String showOrganisation(@PathVariable String organisationId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(organisationId);
        Organization organization = organisationService.getOrganization(longId);
        model.addAttribute("organization", organization);
        model.addAttribute("organization_tutors", tutorService.getOrganizationTutorsByOrganization(organization));
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organisation-info";
    }

}
