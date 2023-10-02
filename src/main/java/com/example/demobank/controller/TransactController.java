package com.example.demobank.controller;

import com.example.demobank.entity.User;
import com.example.demobank.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/transact")
public class TransactController {

    private final AccountRepository accountRepository;

    public TransactController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount")String depositAmount,
                          @RequestParam("account_id")String accountId,
                          HttpSession session,
                          RedirectAttributes redirectAttributes
                          ) {

        if (depositAmount == null || accountId == null) {
            redirectAttributes.addFlashAttribute("error", "Deposit amount and Account Id should not be empty");
            return "redirect:/app/dashboard";
        }

        User user = (User) session.getAttribute("user");

        int acc_id = Integer.parseInt(accountId);

        double depositAmountValue = Double.parseDouble(depositAmount);


        if (depositAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Deposit amount must be more than zero");
            return "redirect:/app/dashboard";
        }

        double currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);
        double newBalance = currentBalance + depositAmountValue;
        accountRepository.changeAccountBalanceById(acc_id, newBalance);

        redirectAttributes.addFlashAttribute("success", "Deposited successfully");
        return "redirect:/app/dashboard";
    }
}
