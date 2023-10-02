package com.example.demobank.repository;

import com.example.demobank.entity.transaction.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {
    @Query("select w from Withdraw w where w.user_id = ?1")
    List<Withdraw> findByUser_id(int user_id);
}
