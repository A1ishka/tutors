package com.makogon.tutor.controller;

import com.makogon.tutor.Role;
import com.makogon.tutor.model.User;
import com.makogon.tutor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller("AuthorityController")
@RequiredArgsConstructor
public class AuthorityController {

    private final UserService userService;
    public User user;

    @GetMapping("/")
    public String showCategories(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("administrator", Role.ADMINISTRATOR);
        return "main-page";
    }

    @GetMapping("/homepage")
    public String showHomePage() {
        return "homepage";
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Ошибка регистрации пользователя с логином: " + user.getLogin());
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
//    @PostMapping("/login")
//    public String login(@RequestParam("login") String login, @RequestParam("password") String password) {
//
//        try {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
//            Authentication authenticated = authenticationManager.authenticate(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authenticated);
//
//            return "redirect:/homepage";
//        } catch (AuthenticationException e) {
//
//            return "redirect:/login?error=true";
//        }
//    }

}