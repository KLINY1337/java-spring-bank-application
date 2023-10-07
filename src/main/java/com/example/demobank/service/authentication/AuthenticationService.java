package com.example.demobank.service.authentication;

import com.example.demobank.entity.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface AuthenticationService {
    String login(String email, String password, String token, Model model, HttpSession session);
    String logout(HttpSession session, RedirectAttributes redirectAttributes);
}
