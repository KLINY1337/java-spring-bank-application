package com.example.demobank.controller;

import com.example.demobank.service.transaction.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/transact")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {this.transactionService = transactionService;}

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount")String depositAmount,
                          @RequestParam("account_id")String accountId,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        return transactionService.deposit(depositAmount, accountId, session, redirectAttributes);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("account_id_from")String accountIdFrom,
                           @RequestParam("account_id_to")String accountIdTo,
                           @RequestParam("transfer_amount") String transferAmount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        return transactionService.transfer(accountIdFrom, accountIdTo, transferAmount, session, redirectAttributes);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawal_amount")String withdrawalAmount,
                           @RequestParam("account_id")String accountId,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        return transactionService.withdraw(withdrawalAmount, accountId, session, redirectAttributes);
    }

    @PostMapping("/payment")
    public String payment(@RequestParam("account_from_id")String accountFromId,
                          @RequestParam("account_to_email")String accountToEmail,
                          @RequestParam("account_to_number")String accountToNumber,
                          @RequestParam("reference")String reference,
                          @RequestParam("payment_amount")String paymentAmount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        return transactionService.payment(accountFromId, accountToEmail, accountToNumber, reference, paymentAmount, session, redirectAttributes);
    }
}
