package com.makogon.tutor.controller;

import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.Organization;
import com.makogon.tutor.service.OrganisationService;
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

    private final OrganisationService organisationService;
    private final AuthorityController authorityController;

    @GetMapping("/organisation")
    public String organisations(Model model) {
        model.addAttribute("organizations", organisationService.getOrganizations());
        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organizations";
    }

    @PostMapping("/organisation/delete/{organisationId}")
    public String deleteOrganisation(@PathVariable String organisationId) {
        long longId = IdToLong.convert(organisationId);
        organisationService.deleteOrganization(longId);
        return "redirect:/organisation";
    }

    @GetMapping("/organisation/create")
    public String createOrganisation() {
        return "organisation-create";
    }

    @PostMapping("/organisation/add")
    public String addOrganisation(Organization organisation) {
        organisationService.saveOrganization(organisation);
        return "redirect:/organisation";
    }

    @PostMapping("/organisation/edit/{organisationId}")
    public String OrganisationEdit(@PathVariable String organisationId, Organization organisation) {
        long longId = IdToLong.convert(organisationId);
        organisationService.editOrganization(longId, organisation);
        return "redirect:/organisation";
    }

    @GetMapping("/organisation/{organisationId}")
    public String showOrganisation(@PathVariable String organisationId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(organisationId);
        model.addAttribute("organisation", organisationService.getOrganization(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "organisation-info";
    }

}
