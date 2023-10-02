package com.example.demobank.repository;

import com.example.demobank.entity.transaction.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    @Query("select t from Transfer t where t.user_id = ?1")
    List<Transfer> findByUser_id(int user_id);
}