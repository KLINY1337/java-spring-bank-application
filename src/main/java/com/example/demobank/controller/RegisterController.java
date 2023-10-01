package com.example.demobank.controller;

import com.example.demobank.entity.User;
import com.example.demobank.helpers.HTML;
import com.example.demobank.helpers.Token;
import com.example.demobank.mail.messenger.MailMessenger;
import com.example.demobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Random;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView getRegisterPage =  new ModelAndView("register");
        getRegisterPage.addObject("PageTitle", "Register");
        return getRegisterPage;
    }

    @PostMapping("/register")
    public ModelAndView register(
            @Valid @ModelAttribute("registerUser") User user,
            BindingResult result,
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirm_password
    ) throws MessagingException {
        ModelAndView registrationPage = new ModelAndView("register");

        //TODO сделать проверку занятости почты




        if(result.hasErrors() && confirm_password.isEmpty()) {
            registrationPage.addObject("confirm_pass", "The confirm field is Required");
            return registrationPage;
        }

        if (!password.equals(confirm_password)) {
            registrationPage.addObject("passwordMisMatch", "Password do not match");
            return registrationPage;
        }

        String token = Token.generateToken();

        Random random = new Random();
        int bound = 123;
        int code = bound * random.nextInt(bound);

        String emailBody = HTML.htmlEmailTemplate(token, Integer.toString(code));

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        User userToRegister = User.builder()
                .first_name(first_name)
                .last_name(last_name)
                .email(email)
                .password(hashed_password)
                .token(token)
                .code(Integer.toString(code))
                .build();
        userRepository.save(userToRegister);

        MailMessenger.htmlEmailMessenger("a12344321123@mail.ru", email, "Verify account", emailBody);

        String successMessage = "Account registered successfully, please check your email and verify account";
        registrationPage.addObject("success", successMessage);

        return registrationPage;
    }
}
