package com.example.demobank.service.transaction;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public interface TransactionService {
    String createDeposit(String depositAmount,
                         String accountId,
                         HttpSession session,
                         RedirectAttributes redirectAttributes);

    String createTransfer(String accountIdFrom,
                          String accountIdTo,
                          String transferAmount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes);

    String createWithdraw(String withdrawalAmount,
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
