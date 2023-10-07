package com.example.demobank.service.transaction;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface TransactionService {
    String deposit(String depositAmount,
                   String accountId,
                   HttpSession session,
                   RedirectAttributes redirectAttributes);

    String transfer(String accountIdFrom,
                    String accountIdTo,
                    String transferAmount,
                    HttpSession session,
                    RedirectAttributes redirectAttributes);

    String withdraw(String withdrawalAmount,
                    String accountId,
                    HttpSession session,
                    RedirectAttributes redirectAttributes);

    String payment(String accountFromId,
                   String accountToEmail,
                   String accountToNumber,
                   String reference,
                   String paymentAmount,
                   HttpSession session,
                   RedirectAttributes redirectAttributes);
}
