package com.example.demobank.service.authentication;

import com.example.demobank.entity.User;
import com.example.demobank.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public String login(String email, String password, String token, Model model, HttpSession session) {
        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Все поля должны быть заполнены");
            return "login";
        }

        String getEmailInDatabase = userRepository.getUserEmail(email);

        if(getEmailInDatabase != null) {
            String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);

            if(!BCrypt.checkpw(password, getPasswordInDatabase)){
                model.addAttribute("error", "Неверное имя пользователя или пароль");
                return "login";
            }

        }
        else {
            model.addAttribute("error", "Упс, что-то пошло не так.");
            return "error";
        }

        int verified = userRepository.isVerified(getEmailInDatabase);
        if (verified != 1) {
            model.addAttribute("error", "Перед входом в аккаунт подтвердите его по почте");
            return "login";
        }

        User user = userRepository.findByEmail(getEmailInDatabase);

        session.setAttribute("user", user);
        session.setAttribute("token", token);
        session.setAttribute("authenticated", true);


        return "redirect:/app/dashboard";
    }

    @Override
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out", "Выход прошёл успешно");
        return "redirect:/login";
    }
}
