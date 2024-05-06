package com.makogon.tutor.controller;

import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.LessonStudent;
import com.makogon.tutor.model.Manager;
import com.makogon.tutor.model.Tutor;
import com.makogon.tutor.model.User;
import com.makogon.tutor.service.ManagerService;
import com.makogon.tutor.service.OrganisationService;
import com.makogon.tutor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller("ManagerController")
@RequiredArgsConstructor
public class ManagerController {

    private final UserService userService;
    private final OrganisationService organisationService;
    private final ManagerService managerService;
    private final AuthorityController authorityController;

    @GetMapping("/managers")
    public String showManagers(Model model) {
        model.addAttribute("managers", managerService.getManager());
//        model.addAttribute("user", authorityController.user);
//        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "managers";
    }

    @PostMapping("/manager/delete/{managerId}")
    public String deleteManager(@PathVariable String managerId) {
        long longId = IdToLong.convert(managerId);
        managerService.deleteManager(longId);
        return "redirect:/manager";
    }

    @GetMapping("/manager/create")
    public String createManager() {
        return "manager-create";
    }

    @PostMapping("/manager/add")
    public String addManager(Manager manager) {
        managerService.saveManager(manager);
        return "redirect:/manager";
    }

    @PostMapping("/manager/edit/{managerId}")
    public String editManager(@PathVariable String managerId, Manager manager) {
        long longId = IdToLong.convert(managerId);
        managerService.editManager(longId, manager);
        return "redirect:/success";
    }

    @GetMapping("/manager/{managerId}")
    public String showManager(@PathVariable String managerId, Model model) throws ChangeSetPersister.NotFoundException {
        long longId = IdToLong.convert(managerId);
        model.addAttribute("manager", managerService.getManager(longId));
        model.addAttribute("user", authorityController.user);
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "manager-info";
    }

    @GetMapping("/manager/me")
    public String showSpecialization(Model model, Principal principal) throws ChangeSetPersister.NotFoundException {
        User user = userService.getUserByPrincipal(principal);
        Manager manager = managerService.findByUserUserId(user.getUserId());
        model.addAttribute("organization", organisationService.getOrganizations());
        model.addAttribute("manager", manager);
        return "manager-info";
    }


}