package com.example.demobank.service.view;

import com.example.demobank.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface ViewService {

    ModelAndView getLoginView();
    ModelAndView getRegisterView();
    ModelAndView getDashboardView(HttpSession session);
    ModelAndView getTransactionHistoryView(HttpSession session);
    ModelAndView getIndexView();
    ModelAndView getErrorView();
    ModelAndView getLoginWithVerificationView(String token, String code);
    ModelAndView getRegisterWithSuccessView(
            User user,
            BindingResult result,
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword);
}
