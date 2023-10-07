package com.example.demobank.repository;

import com.example.demobank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByAccountFrom_User(User user);

}