package com.makogon.tutor.service;

import com.makogon.tutor.Role;
import com.makogon.tutor.model.User;
import com.makogon.tutor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service("UserService")
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User createUser(User user){
        if(userRepository.findByLogin(user.getLogin())!=null){
            return null;
        }
        user.setRole(userRepository.findAll().isEmpty() ? Role.ADMINISTRATOR : Role.STUDENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new user with login {}",user.getLogin());
        userRepository.save(user);
        return user;
    }

    public List<User> getUserByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal==null)return new User();
        return userRepository.findByLogin(principal.getName());
    }

    public void editUser(User newUser, User olduser) {
        newUser.setUserId(olduser.getUserId());
        newUser.setPassword(olduser.getPassword());
        newUser.setRole(olduser.getRole());
        userRepository.save(newUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void setManagerRole(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(Role.MANAGER);
        userRepository.save(user);
    }

    public List<User> getManagers() {
        return userRepository.findByRole(Role.MANAGER);
    }
}