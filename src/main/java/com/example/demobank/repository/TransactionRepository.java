package com.example.demobank.repository;

import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t from Transaction t WHERE t.transactionType = 'DEPOSIT' AND t.accountTo = :user")
    List<Transaction> findAllDepositsCommittedByUser(@Param("user") User user);

    @Query(value = "SELECT t FROM Transaction t WHERE t.transactionType = 'TRANSFER' AND t.accountTo = t.accountFrom AND t.accountTo = :user")
    List<Transaction> findAllTransfersCommittedByUser(@Param("user") User user);

    @Query(value = "SELECT t FROM Transaction t WHERE t.transactionType = 'PAYMENT' AND (t.accountTo = :user OR t.accountFrom = :user)")
    List<Transaction> findAllPaymentsCommittedByOrToUserByUser(@Param("user") User user);

    @Query(value = "SELECT t from Transaction t WHERE t.transactionType = 'WITHDRAW' AND t.accountFrom = :user")
    List<Transaction> findAllWithdrawsCommittedByUser(@Param("user") User user);
}