package com.example.demobank.controller;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Deposit;
import com.example.demobank.entity.transaction.Payment;
import com.example.demobank.entity.transaction.Transfer;
import com.example.demobank.entity.transaction.Withdraw;
import com.example.demobank.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class AppController {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;
    private final DepositRepository depositRepository;
    private final TransferRepository transferRepository;
    private final WithdrawRepository withdrawRepository;

    public AppController(AccountRepository accountRepository,
                         PaymentRepository paymentRepository,
                         DepositRepository depositRepository,
                         TransferRepository transferRepository,
                         WithdrawRepository withdrawRepository
    ) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.depositRepository = depositRepository;
        this.transferRepository = transferRepository;
        this.withdrawRepository = withdrawRepository;
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

    @GetMapping("/dashboard/history")
    public ModelAndView getTransactionHistory(HttpSession session) {
        ModelAndView getTransactionHistoryPage = new ModelAndView("transaction_history");

        User user = (User) session.getAttribute("user");

        List<Payment> paymentsFromUser = paymentRepository.findByUser_from_id(user.getUser_id());
        List<Payment> paymentsToUser = paymentRepository.findByUser_to_id(user.getUser_id());
        List<Payment> payments = new ArrayList<>();
        payments.addAll(paymentsFromUser);
        payments.addAll(paymentsToUser);
        payments = payments.stream().sorted(Comparator.comparing(Payment::getCreated_at)).collect(Collectors.toList());
        getTransactionHistoryPage.addObject("payments", payments);

        List<Deposit> deposits = depositRepository.findByUser_id(user.getUser_id());
        getTransactionHistoryPage.addObject("deposits", deposits);

        List<Transfer> transfers = transferRepository.findByUser_id(user.getUser_id());
        getTransactionHistoryPage.addObject("transfers", transfers);

        List<Withdraw> withdraws = withdrawRepository.findByUser_id(user.getUser_id());
        getTransactionHistoryPage.addObject("withdraws", withdraws);

        return getTransactionHistoryPage;
    }
}
