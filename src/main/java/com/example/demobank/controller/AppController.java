package com.example.demobank.controller;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.User;
import com.example.demobank.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {

    private final AccountRepository accountRepository;

    public AppController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");

        User user = (User) session.getAttribute("user");

        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

        BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

        getDashboardPage.addObject("userAccounts", getUserAccounts);
        getDashboardPage.addObject("totalBalance", totalAccountsBalance);
        return getDashboardPage;
    }
}
