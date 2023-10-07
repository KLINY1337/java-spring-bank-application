package com.example.demobank.repository;

import com.example.demobank.entity.User;
import com.example.demobank.entity.transaction.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Transfer, Long> {

    @Query(value = "SELECT p FROM Transfer p WHERE p.accountFrom = :user OR p.accountTo = :user ORDER BY p.createdAt DESC")
    List<Transfer> findAllPaymentsWhereUserIsSenderOrReceiverByUser(@Param("user") User user);
}
