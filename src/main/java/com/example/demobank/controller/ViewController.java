package com.example.demobank.controller;

import com.example.demobank.entity.User;
import com.example.demobank.service.view.ViewService;
import com.example.demobank.service.view.ViewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewServiceImpl viewService) {this.viewService = viewService;}

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return viewService.getLoginView();
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        return viewService.getRegisterView();
    }

    @GetMapping("/app/dashboard")
    public ModelAndView getDashboard(HttpSession session) {
        return viewService.getDashboardView(session);
    }

    @GetMapping("/app/dashboard/history")
    public ModelAndView getTransactionHistory(HttpSession session) {
        return viewService.getTransactionHistoryView(session);
    }

    @GetMapping("/")
    public ModelAndView getIndex() {
        return viewService.getIndexView();
    }

    @GetMapping("/error")
    public ModelAndView getError() {
        return viewService.getErrorView();
    }

    @GetMapping("/verify")
    public ModelAndView getVerify(@RequestParam("token") String token, @RequestParam("code") String code) {
        return viewService.getLoginWithVerificationView(token, code);
    }

    @PostMapping("/register")
    public ModelAndView register(
            @Valid @ModelAttribute("registerUser") User user,
            BindingResult result,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirmPassword) {
        return viewService.getRegisterWithSuccessView(user, result, firstName, lastName, email, password, confirmPassword);
    }
}
