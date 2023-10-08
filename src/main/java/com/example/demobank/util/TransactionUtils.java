package com.example.demobank.util;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.transaction.Transaction;
import com.example.demobank.entity.transaction.TransactionType;
import com.example.demobank.repository.AccountRepository;
import com.example.demobank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TransactionUtils {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionUtils(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void performDepositTransaction(String accountId, BigDecimal amount) {

        Account accountForDeposit = accountRepository.findById(accountId).get();
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.DEPOSIT)
                .accountFrom(accountRepository.getDepositAccount())
                .accountTo(accountForDeposit)
                .amount(amount)
                .reference("Ввод средств на счёт")
                .createdAt(LocalDateTime.now())
                .build();

        performTransaction(transaction);
    }

    public void performTransferTransaction(String accountIdFrom, String accountIdTo, BigDecimal amount) {

        Account accountFrom = accountRepository.findById(accountIdFrom).get();
        Account accountTo = accountRepository.findById(accountIdTo).get();
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.TRANSFER)
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .amount(amount)
                .reference("Перевод между своими счетами")
                .createdAt(LocalDateTime.now())
                .build();

        performTransaction(transaction);
    }

    private void performTransaction(Transaction transaction) {
        Account accountFrom = transaction.getAccountFrom();
        Account accountTo = transaction.getAccountTo();
        BigDecimal amount = transaction.getAmount();

        accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
        accountTo.setBalance(accountTo.getBalance().add(amount));

        transactionRepository.save(transaction);
    }
}
