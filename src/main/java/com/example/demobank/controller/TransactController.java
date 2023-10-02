package com.example.demobank.controller;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.transaction.Deposit;
import com.example.demobank.entity.transaction.Payment;
import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Transfer;
import com.example.demobank.entity.transaction.Withdraw;
import com.example.demobank.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transact")
public class TransactController {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final DepositRepository depositRepository;
    private final TransferRepository transferRepository;
    private final WithdrawRepository withdrawRepository;

    public TransactController(AccountRepository accountRepository,
                              UserRepository userRepository,
                              PaymentRepository paymentRepository,
                              DepositRepository depositRepository,
                              TransferRepository transferRepository,
                              WithdrawRepository withdrawRepository
    ) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.depositRepository = depositRepository;
        this.transferRepository = transferRepository;
        this.withdrawRepository = withdrawRepository;
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

        Deposit deposit = new Deposit();
        deposit.setAccount_id(acc_id);
        deposit.setUser_id(user.getUser_id());
        deposit.setAccount_name(accountRepository.findById(acc_id).get().getAccount_name());
        deposit.setAmount(BigDecimal.valueOf(depositAmountValue));
        deposit.setCreated_at(LocalDateTime.now());

        depositRepository.save(deposit);

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

        Transfer transfer = new Transfer();
        transfer.setUser_id(user.getUser_id());
        transfer.setAccount_from_id(Integer.parseInt(accountIdFrom));
        transfer.setAccount_from_name(accountRepository.findById(Integer.valueOf(accountIdFrom)).get().getAccount_name());
        transfer.setAccount_to_id(Integer.parseInt(accountIdTo));
        transfer.setAccount_to_name(accountRepository.findById(Integer.valueOf(accountIdTo)).get().getAccount_name());
        transfer.setAmount(BigDecimal.valueOf(transferAmountValue));
        transfer.setCreated_at(LocalDateTime.now());

        transferRepository.save(transfer);

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

        Withdraw withdraw = new Withdraw();
        withdraw.setUser_id(user.getUser_id());
        withdraw.setAccount_id(Integer.parseInt(accountId));
        withdraw.setAccount_name(accountRepository.findById(Integer.valueOf(accountId)).get().getAccount_name());
        withdraw.setAmount(BigDecimal.valueOf(withdrawAmountValue));
        withdraw.setCreated_at(LocalDateTime.now());
        withdrawRepository.save(withdraw);

        return "redirect:/app/dashboard";
    }

    @PostMapping("/payment")
    public String payment(@RequestParam("account_from_id")String accountFromId,
                          @RequestParam("account_to_email")String accountToEmail,
                          @RequestParam("account_to_number")String accountToNumber,
                          @RequestParam("reference")String reference,
                          @RequestParam("payment_amount")String paymentAmount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        if (accountFromId.isEmpty() || accountToEmail.isEmpty() || accountToNumber.isEmpty() || paymentAmount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "All fields instead of reference must be filled");
            return "redirect:/app/dashboard";
        }

        if (!userRepository.existsByEmail(accountToEmail)) {
            redirectAttributes.addFlashAttribute("error", "User you are trying to pay doesn't exist");
            return "redirect:/app/dashboard";
        }

        User userFrom = (User) session.getAttribute("user");
        User userTo = userRepository.findByEmail(accountToEmail);

        List<Account> userToAccounts = accountRepository.getUserAccountsById(userTo.getUser_id());
        Optional<Account> userToAccount = userToAccounts.stream().filter(account -> account.getAccount_number().equals(accountToNumber)).findAny();

        if (!userToAccount.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "User you are trying to pay doesn't have specified account");
            return "redirect:/app/dashboard";
        }

        double paymentAmountValue = Double.parseDouble(paymentAmount);
        if (paymentAmountValue > accountRepository.getAccountBalance(userFrom.getUser_id(), Integer.parseInt(accountFromId))) {
            redirectAttributes.addFlashAttribute("error", "Payment amount must less or equal account FROM balance");
            return "redirect:/app/dashboard";
        }

        Payment payment = new Payment();
        payment.setUser_from_id(userFrom.getUser_id());
        payment.setUser_from_name(userFrom.getFirst_name() + " " + userFrom.getLast_name());
        payment.setUser_from_account_name(accountRepository.findById(Integer.parseInt(accountFromId)).get().getAccount_name());
        payment.setUser_to_id(userTo.getUser_id());
        payment.setUser_to_email(accountToEmail);
        payment.setUser_to_account_id(userToAccount.orElseThrow().getAccount_id());
        payment.setUser_to_account_number(Integer.parseInt(accountToNumber));
        payment.setAmount(paymentAmountValue);
        payment.setReference(reference);
        payment.setCreated_at(LocalDateTime.now());

        paymentRepository.save(payment);

        double currentAccountFromBalance = accountRepository.getAccountBalance(userFrom.getUser_id(), Integer.parseInt(accountFromId));
        double currentAccountToBalance = userToAccount.get().getBalance().doubleValue();
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountFromId), currentAccountFromBalance - paymentAmountValue);
        accountRepository.changeAccountBalanceById(userToAccount.get().getAccount_id(), currentAccountToBalance + paymentAmountValue);

        redirectAttributes.addFlashAttribute("success", "Payment proceeded successfully");
        return "redirect:/app/dashboard";
    }
}
