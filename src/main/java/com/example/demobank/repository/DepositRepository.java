package com.example.demobank.repository;

import com.example.demobank.entity.transaction.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {
    @Query("select d from Deposit d where d.user_id = ?1")
    List<Deposit> findByUser_id(int user_id);
}
