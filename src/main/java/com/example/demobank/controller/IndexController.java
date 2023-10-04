package com.example.demobank.controller;

import com.example.demobank.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ModelAndView getIndex() {
        ModelAndView getIndexPage =  new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        return getIndexPage;
    }




    @GetMapping("/error")
    public ModelAndView getError() {
        ModelAndView getErrorPage =  new ModelAndView("error");
        getErrorPage.addObject("PageTitle", "Error");
        return getErrorPage;
    }

    @GetMapping("/verify")
    public ModelAndView getVerify(@RequestParam("token") String token, @RequestParam("code") String code) {

        ModelAndView getVerifyPage =  new ModelAndView("login");

        String dbToken = userRepository.checkToken(token);
        if (dbToken == null || dbToken.isEmpty()) {
            ModelAndView getErrorPage = new ModelAndView("error");
            getErrorPage.addObject("PageTitle", "Срок действия сеанса истёк");
            return getErrorPage;
        }
        userRepository.updateVerifiedAndVerified_atAndUpdated_atByTokenAndCode(token, code);

        getVerifyPage.addObject("success", "Аккаунт успешно подтвержден, теперь в него можно войти");
        return getVerifyPage;
    }
}
