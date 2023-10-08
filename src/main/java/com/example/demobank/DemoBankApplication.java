package com.example.demobank;

import com.example.demobank.entity.Account;
import com.example.demobank.entity.AccountType;
import com.example.demobank.entity.User;
import com.example.demobank.repository.AccountRepository;
import com.example.demobank.repository.UserRepository;
import com.example.demobank.util.AccountNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
public class DemoBankApplication {

    private static ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(DemoBankApplication.class, args);

        User systemUser = User.builder()
                .firstName("SYSTEM ACCOUNT")
                .lastName("SYSTEM ACCOUNT")
                .isVerified(true)
                .password(UUID.randomUUID().toString())
                .build();

        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.save(systemUser);

        AccountRepository accountRepository = context.getBean(AccountRepository.class);

        Account depositAccount = Account.builder()
                .id(AccountNumber.generate())
                .user(systemUser)
                .name("Deposit account")
                .type(AccountType.SYSTEM_ACCOUNT)
                .balance(BigDecimal.valueOf(Integer.MAX_VALUE))
                .createdAt(LocalDateTime.now())
                .build();

        Account withdrawAccount = Account.builder()
                .id(AccountNumber.generate())
                .user(systemUser)
                .name("Withdraw account")
                .type(AccountType.SYSTEM_ACCOUNT)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        accountRepository.save(depositAccount);
        accountRepository.save(withdrawAccount);
    }

}
