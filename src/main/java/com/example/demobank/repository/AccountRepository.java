package com.example.demobank.repository;

import com.example.demobank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "SELECT * from accounts where user_id = :user_id", nativeQuery = true)
    List<Account> getUserAccountsById(@Param("user_id")int user_id);

    @Query(value = "SELECT sum(balance) from accounts where user_id = :user_id", nativeQuery = true)
    BigDecimal getTotalBalance(@Param("user_id")int user_id);

    @Query(value = "SELECT balance from accounts where user_id = :user_id AND account_id = :account_id", nativeQuery = true)
    double getAccountBalance(@Param("user_id")int user_id,
                           @Param("account_id")int account_id);

    @Query(value = "UPDATE accounts set balance = :balance where account_id = :account_id", nativeQuery = true)
    @Modifying
    @Transactional
    void changeAccountBalanceById(@Param("account_id")int account_id,
                                  @Param("balance")double balance);

    @Query(value = "INSERT INTO accounts(account_id, user_id, account_number, account_name, account_type, created_at) VALUES (:account_number, :user_id, :account_number, :account_name, :account_type, now())", nativeQuery = true)
    @Modifying
    @Transactional
    void createBankAccount(@Param("user_id")int user_id,
                           @Param("account_number")String account_number,
                           @Param("account_name")String account_name,
                           @Param("account_type")String account_type);


}
