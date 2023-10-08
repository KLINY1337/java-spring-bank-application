package com.example.demobank.service.transaction;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Transaction;
import com.example.demobank.repository.*;
import com.example.demobank.util.TransactionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionUtils transactionUtils;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, UserRepository userRepository, TransactionUtils transactionUtils) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transactionUtils = transactionUtils;
    }

    @Override
    public String createDeposit(String depositAmount, String accountId, HttpSession session, RedirectAttributes redirectAttributes) {
        if (depositAmount.isEmpty() || accountId.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Все поля должны быть заполнены");
            return "redirect:/app/dashboard";
        }

        boolean isAccountExists = accountRepository.existsById(accountId);
        if (!isAccountExists) {
            redirectAttributes.addFlashAttribute("error", "Указанного аккаунта не существует");
            return "redirect:/app/dashboard";
        }

        double depositAmountValue = Double.parseDouble(depositAmount);
        if (depositAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Введите сумму больше нуля");
            return "redirect:/app/dashboard";
        }

        transactionUtils.performDepositTransaction(accountId, BigDecimal.valueOf(depositAmountValue));

        redirectAttributes.addFlashAttribute("success", "Средства зачислены на ваш счёт");
        return "redirect:/app/dashboard";
    }

    @Override
    public String createTransfer(String accountIdFrom, String accountIdTo, String transferAmount, HttpSession session, RedirectAttributes redirectAttributes) {
        if (accountIdFrom.isEmpty() || accountIdTo.isEmpty() || transferAmount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Все поля должны быть заполнены");
            return "redirect:/app/dashboard";
        }

        boolean isAccountFromExists = accountRepository.existsById(accountIdFrom);
        if (!isAccountFromExists) {
            redirectAttributes.addFlashAttribute("error", "Указанного счёта отправления не существует");
            return "redirect:/app/dashboard";
        }

        boolean isAccountToExists = accountRepository.existsById(accountIdTo);
        if (!isAccountToExists) {
            redirectAttributes.addFlashAttribute("error", "Указанного счёта получения не существует");
            return "redirect:/app/dashboard";
        }

        if (accountIdFrom.equals(accountIdTo)) {
            redirectAttributes.addFlashAttribute("error", "Перевод не может быть проведен, если аккаунт отправления и получения совпадают");
            return "redirect:/app/dashboard";
        }

        double transferAmountValue = Double.parseDouble(transferAmount);
        if (transferAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Сумма перевода должна быть больше нуля");
            return "redirect:/app/dashboard";
        }

        transactionUtils.performTransferTransaction(accountIdFrom, accountIdTo, BigDecimal.valueOf(transferAmount));

        redirectAttributes.addFlashAttribute("success", "Перевод соврешен успешно");
        return "redirect:/app/dashboard";
    }

    @Override
    public String createWithdraw(String withdrawalAmount, String accountId, HttpSession session, RedirectAttributes redirectAttributes) {
        if (withdrawalAmount.isEmpty() || accountId.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Все поля должны быть заполнены");
            return "redirect:/app/dashboard";
        }

        User user = (User) session.getAttribute("user");

        double withdrawAmountValue = Double.parseDouble(withdrawalAmount);
        if (withdrawAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Сумма вывода должна быть больше нуля");
            return "redirect:/app/dashboard";
        }

        double currentBalance = accountRepository.getAccountBalance(user.getId(), Integer.parseInt(accountId));

        if (withdrawAmountValue > currentBalance) {
            redirectAttributes.addFlashAttribute("error", "Недостаточно средств для вывода");
            return "redirect:/app/dashboard";
        }

        accountRepository.changeAccountBalanceById(Integer.parseInt(accountId), currentBalance - withdrawAmountValue);
        redirectAttributes.addFlashAttribute("success", "Вывод прошёл успешно");

        Withdraw withdraw = new Withdraw();
        withdraw.setUser_id(user.getId());
        withdraw.setAccount_id(Integer.parseInt(accountId));
        withdraw.setAccount_name(accountRepository.findById(Integer.valueOf(accountId)).get().getAccount_name());
        withdraw.setAmount(BigDecimal.valueOf(withdrawAmountValue));
        withdraw.setCreated_at(LocalDateTime.now());
        withdrawRepository.save(withdraw);

        return "redirect:/app/dashboard";
    }

    @Override
    public String payment(String accountFromId, String accountToEmail, String accountToNumber, String reference, String paymentAmount, HttpSession session, RedirectAttributes redirectAttributes) {
        if (accountFromId.isEmpty() || accountToEmail.isEmpty() || accountToNumber.isEmpty() || paymentAmount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Все поля кроме комментария должны быть заполнены");
            return "redirect:/app/dashboard";
        }

        if (!userRepository.existsByEmail(accountToEmail)) {
            redirectAttributes.addFlashAttribute("error", "Лицо, которому вы совершаете перевод, незарегистрированно в нашем банке, перевод отменён");
            return "redirect:/app/dashboard";
        }

        User userFrom = (User) session.getAttribute("user");
        User userTo = userRepository.findByEmail(accountToEmail);

        List<Account> userToAccounts = accountRepository.getUserAccountsById(userTo.getId());
        Optional<Account> userToAccount = userToAccounts.stream().filter(account -> account.getAccount_number().equals(accountToNumber)).findAny();

        if (!userToAccount.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Лицо, которому вы совершаете перевод, не имеет указанного вами счёта, перевод отменён");
            return "redirect:/app/dashboard";
        }

        double paymentAmountValue = Double.parseDouble(paymentAmount);
        if (paymentAmountValue > accountRepository.getAccountBalance(userFrom.getId(), Integer.parseInt(accountFromId))) {
            redirectAttributes.addFlashAttribute("error", "Недостаточно средств для перевода");
            return "redirect:/app/dashboard";
        }

        Transaction transaction = new Transaction();
        transaction.setUser_from_id(userFrom.getId());
        transaction.setUser_from_name(userFrom.getFirst_name() + " " + userFrom.getLast_name());
        transaction.setUser_from_account_name(accountRepository.findById(Integer.parseInt(accountFromId)).get().getAccount_name());
        transaction.setUser_to_id(userTo.getId());
        transaction.setUser_to_email(accountToEmail);
        transaction.setUser_to_account_id(userToAccount.orElseThrow().getAccount_id());
        transaction.setUser_to_account_number(Integer.parseInt(accountToNumber));
        transaction.setAmount(paymentAmountValue);
        transaction.setReference(reference);
        transaction.setCreated_at(LocalDateTime.now());

        paymentRepository.save(transaction);

        double currentAccountFromBalance = accountRepository.getAccountBalance(userFrom.getId(), Integer.parseInt(accountFromId));
        double currentAccountToBalance = userToAccount.get().getBalance().doubleValue();
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountFromId), currentAccountFromBalance - paymentAmountValue);
        accountRepository.changeAccountBalanceById(userToAccount.get().getAccount_id(), currentAccountToBalance + paymentAmountValue);

        redirectAttributes.addFlashAttribute("success", "Перевод прошёл успешно");
        return "redirect:/app/dashboard";
    }
}
