package com.example.demobank.controller;

import com.example.demobank.entity.User;
import com.example.demobank.repository.AccountRepository;
import com.example.demobank.repository.UserRepository;
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
    private final UserRepository userRepository;

    public TransactController(AccountRepository accountRepository,
                              UserRepository userRepository
    ) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount")String depositAmount,
                          @RequestParam("account_id")String accountId,
                          HttpSession session,
                          RedirectAttributes redirectAttributes
                          ) {

        if (depositAmount.isEmpty() || accountId.isEmpty()) {
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

    @PostMapping("/transfer")
    public String transfer(@RequestParam("account_id_from")String accountIdFrom,
                           @RequestParam("account_id_to")String accountIdTo,
                           @RequestParam("transfer_amount") String transferAmount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        if (accountIdFrom.isEmpty() || accountIdTo.isEmpty() || transferAmount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "All fields must be filled");
            return "redirect:/app/dashboard";
        }

        if (accountIdFrom.equals(accountIdTo)) {
            redirectAttributes.addFlashAttribute("error", "Transfer cannot be proceed between the same accounts");
            return "redirect:/app/dashboard";
        }

        double transferAmountValue = Double.parseDouble(transferAmount);
        if (transferAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Transfer amount must be more than zero");
            return "redirect:/app/dashboard";
        }

        User user = (User) session.getAttribute("user");
        if (transferAmountValue > accountRepository.getAccountBalance(user.getUser_id(), Integer.parseInt(accountIdFrom))) {
            redirectAttributes.addFlashAttribute("error", "Transfer amount must be less or equal FROM account balance");
            return "redirect:/app/dashboard";
        }

        double currentFromBalance = accountRepository.getAccountBalance(user.getUser_id(), Integer.parseInt(accountIdFrom));
        double currentToBalance = accountRepository.getAccountBalance(user.getUser_id(), Integer.parseInt(accountIdTo));
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountIdFrom), currentFromBalance - transferAmountValue);
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountIdTo), currentToBalance + transferAmountValue);
        redirectAttributes.addFlashAttribute("success", "Transferred successfully");

        return "redirect:/app/dashboard";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawal_amount")String withdrawalAmount,
                           @RequestParam("account_id")String accountId,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (withdrawalAmount.isEmpty() || accountId.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "All fields must be filled");
            return "redirect:/app/dashboard";
        }

        User user = (User) session.getAttribute("user");

        double withdrawAmountValue = Double.parseDouble(withdrawalAmount);
        if (withdrawAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Withdraw amount must be more than zero");
            return "redirect:/app/dashboard";
        }

        double currentBalance = accountRepository.getAccountBalance(user.getUser_id(), Integer.parseInt(accountId));

        if (withdrawAmountValue > currentBalance) {
            redirectAttributes.addFlashAttribute("error", "Withdraw amount must less or equal account balance");
            return "redirect:/app/dashboard";
        }

        accountRepository.changeAccountBalanceById(Integer.parseInt(accountId), currentBalance - withdrawAmountValue);
        redirectAttributes.addFlashAttribute("success", "Withdrawed successfully");

        return "redirect:/app/dashboard";
    }
}
