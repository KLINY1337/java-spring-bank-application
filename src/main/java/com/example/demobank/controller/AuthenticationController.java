package com.example.demobank.controller;

import com.example.demobank.repository.UserRepository;
import com.example.demobank.service.authentication.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        @RequestParam("_token")String token,
                        Model model,
                        HttpSession session) {
       return authenticationService.login(email, password, token, model, session);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        return authenticationService.logout(session, redirectAttributes);
    }


}
