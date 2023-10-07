package com.example.demobank.service.account;

import com.example.demobank.entity.User;
import com.example.demobank.util.AccountNumber;
import com.example.demobank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {this.accountRepository = accountRepository;}

    @Override
    public String createAccount(String accountName, String accountType, RedirectAttributes redirectAttributes, HttpSession session) {
        if (accountName.isEmpty() || accountType.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Заполните все поля");
            return "redirect:/app/dashboard";
        }

        User user = (User) session.getAttribute("user");

        int setAccountNumber = AccountNumber.generate();
        String bankAccountNumber = Integer.toString(setAccountNumber);

        accountRepository.createBankAccount(user.getId(), bankAccountNumber, accountName, accountType);
        redirectAttributes.addFlashAttribute("success", "Счёт создан успешно");
        return "redirect:/app/dashboard";
    }
}
