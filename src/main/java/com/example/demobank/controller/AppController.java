package com.example.demobank.controller;

import com.example.demobank.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AppController {

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");

        User user = (User) session.getAttribute("user");

        return getDashboardPage;
    }
}
