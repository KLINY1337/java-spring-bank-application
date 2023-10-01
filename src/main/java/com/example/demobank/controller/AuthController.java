package com.example.demobank.controller;

import com.example.demobank.entity.User;
import com.example.demobank.helpers.Token;
import com.example.demobank.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView getLoginPage =  new ModelAndView("login");
        String token = Token.generateToken();
        getLoginPage.addObject("token", token);
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        @RequestParam("_token")String token,
                        Model model,
                        HttpSession session){

        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Login and password must not be empty");
            return "login";
        }

        String getEmailInDatabase = userRepository.getUserEmail(email);

        if(getEmailInDatabase != null) {
            String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);

            if(!BCrypt.checkpw(password, getPasswordInDatabase)){
                model.addAttribute("error", "Incorrect username or password");
                return "login";
            }

        }
        else {
            model.addAttribute("error", "Something went wrong please contact support");
            return "error";
        }

        int verified = userRepository.isVerified(getEmailInDatabase);
        if (verified != 1) {
            model.addAttribute("error", "Before logging in, please verify your account via email");
            return "login";
        }

        User user = userRepository.findByEmail(getEmailInDatabase);

        session.setAttribute("user", user);
        session.setAttribute("token", token);
        session.setAttribute("authenticated", true);


        return "redirect:/app/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();

        redirectAttributes.addFlashAttribute("logged_out", "Logged out successfully");
        return "redirect:/login";
    }
}
