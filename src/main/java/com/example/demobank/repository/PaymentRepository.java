package com.example.demobank.repository;

import com.example.demobank.entity.transaction.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("select p from Payment p where p.user_to_id = ?1")
    List<Payment> findByUser_to_id(int user_to_id);
    @Query("select p from Payment p where p.user_from_id = ?1")
    List<Payment> findByUser_from_id(int user_from_id);
}
