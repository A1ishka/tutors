package com.makogon.tutor.configs;

import com.makogon.tutor.controller.AuthorityController;
import com.makogon.tutor.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginSListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final AuthorityController authorityController;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent evt) {
        authorityController.user = (User) evt.getAuthentication().getPrincipal();
//        System.out.println(authorityController.user.getUsername() + " has just logged in");
    }
}