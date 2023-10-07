package com.example.demobank.service.transaction;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Transfer;
import com.example.demobank.repository.*;
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
    private final DepositRepository depositRepository;
    private final TransferRepository transferRepository;
    private final WithdrawRepository withdrawRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, DepositRepository depositRepository, TransferRepository transferRepository, WithdrawRepository withdrawRepository, UserRepository userRepository) {this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
        this.transferRepository = transferRepository;
        this.withdrawRepository = withdrawRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String deposit(String depositAmount, String accountId, HttpSession session, RedirectAttributes redirectAttributes) {
        if (depositAmount.isEmpty() || accountId.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Все поля должны быть заполнены");
            return "redirect:/app/dashboard";
        }

        User user = (User) session.getAttribute("user");

        int acc_id = Integer.parseInt(accountId);

        double depositAmountValue = Double.parseDouble(depositAmount);


        if (depositAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Введите сумму больше нуля");
            return "redirect:/app/dashboard";
        }

        double currentBalance = accountRepository.getAccountBalance(user.getId(), acc_id);
        double newBalance = currentBalance + depositAmountValue;
        accountRepository.changeAccountBalanceById(acc_id, newBalance);

        Deposit deposit = new Deposit();
        deposit.setAccount_id(acc_id);
        deposit.setUser_id(user.getId());
        deposit.setAccount_name(accountRepository.findById(acc_id).get().getAccount_name());
        deposit.setAmount(BigDecimal.valueOf(depositAmountValue));
        deposit.setCreated_at(LocalDateTime.now());

        depositRepository.save(deposit);

        redirectAttributes.addFlashAttribute("success", "Средства зачислены на ваш счёт");
        return "redirect:/app/dashboard";
    }

    @Override
    public String transfer(String accountIdFrom, String accountIdTo, String transferAmount, HttpSession session, RedirectAttributes redirectAttributes) {
        if (accountIdFrom.isEmpty() || accountIdTo.isEmpty() || transferAmount.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Все поля должны быть заполнены");
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

        User user = (User) session.getAttribute("user");
        if (transferAmountValue > accountRepository.getAccountBalance(user.getId(), Integer.parseInt(accountIdFrom))) {
            redirectAttributes.addFlashAttribute("error", "Недостаточно средств на аккаунте отправления");
            return "redirect:/app/dashboard";
        }

        double currentFromBalance = accountRepository.getAccountBalance(user.getId(), Integer.parseInt(accountIdFrom));
        double currentToBalance = accountRepository.getAccountBalance(user.getId(), Integer.parseInt(accountIdTo));
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountIdFrom), currentFromBalance - transferAmountValue);
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountIdTo), currentToBalance + transferAmountValue);
        redirectAttributes.addFlashAttribute("success", "Перевод прошёл успешно");

        Transfer transfer = new Transfer();
        transfer.setUser_id(user.getId());
        transfer.setAccount_from_id(Integer.parseInt(accountIdFrom));
        transfer.setAccount_from_name(accountRepository.findById(Integer.valueOf(accountIdFrom)).get().getAccount_name());
        transfer.setAccount_to_id(Integer.parseInt(accountIdTo));
        transfer.setAccount_to_name(accountRepository.findById(Integer.valueOf(accountIdTo)).get().getAccount_name());
        transfer.setAmount(BigDecimal.valueOf(transferAmountValue));
        transfer.setCreated_at(LocalDateTime.now());

        transferRepository.save(transfer);

        return "redirect:/app/dashboard";
    }

    @Override
    public String withdraw(String withdrawalAmount, String accountId, HttpSession session, RedirectAttributes redirectAttributes) {
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

        Transfer transfer = new Transfer();
        transfer.setUser_from_id(userFrom.getId());
        transfer.setUser_from_name(userFrom.getFirst_name() + " " + userFrom.getLast_name());
        transfer.setUser_from_account_name(accountRepository.findById(Integer.parseInt(accountFromId)).get().getAccount_name());
        transfer.setUser_to_id(userTo.getId());
        transfer.setUser_to_email(accountToEmail);
        transfer.setUser_to_account_id(userToAccount.orElseThrow().getAccount_id());
        transfer.setUser_to_account_number(Integer.parseInt(accountToNumber));
        transfer.setAmount(paymentAmountValue);
        transfer.setReference(reference);
        transfer.setCreated_at(LocalDateTime.now());

        paymentRepository.save(transfer);

        double currentAccountFromBalance = accountRepository.getAccountBalance(userFrom.getId(), Integer.parseInt(accountFromId));
        double currentAccountToBalance = userToAccount.get().getBalance().doubleValue();
        accountRepository.changeAccountBalanceById(Integer.parseInt(accountFromId), currentAccountFromBalance - paymentAmountValue);
        accountRepository.changeAccountBalanceById(userToAccount.get().getAccount_id(), currentAccountToBalance + paymentAmountValue);

        redirectAttributes.addFlashAttribute("success", "Перевод прошёл успешно");
        return "redirect:/app/dashboard";
    }
}
