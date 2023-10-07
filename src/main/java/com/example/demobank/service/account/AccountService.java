package com.example.demobank.service.account;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface AccountService {
    String createAccount(String accountName,
                         String accountType,
                         RedirectAttributes redirectAttributes,
                         HttpSession session
    );
}
