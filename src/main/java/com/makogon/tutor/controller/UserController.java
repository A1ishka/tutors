package com.makogon.tutor.controller;
import com.makogon.tutor.IdToLong;
import com.makogon.tutor.Role;
import com.makogon.tutor.model.User;
import com.makogon.tutor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("UserController")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorityController authorityController;

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable String id){
        Long longId= IdToLong.convert(id);
        userService.deleteUser(longId);
        return "login";
    }

    @GetMapping("/user")
    public String user(Model model){
        model.addAttribute("user", authorityController.user);
        return "user-info";
    }

    @GetMapping("/user/edit")
    public String editUser(Model model){
        model.addAttribute("user", authorityController.user);
        return "user-edit";
    }

    @PostMapping("/user/update")
    public String editingUser(User newUser){
        userService.editUser(newUser, authorityController.user);
        return "login";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getUserByRole(Role.USER));
        return "users";
    }

    @PostMapping("/user/{id}/set-role")
    public String setRole(@PathVariable String id) {
        Long userId= IdToLong.convert(id);
        userService.setManagerRole(userId);
        return "redirect:/users";
    }
}
