package com.example.demobank.entity.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "withdraws")
public class Withdraw {

    @Id
    @GeneratedValue
    private int withdraw_id;
    private int user_id;
    private int account_id;
    private String account_name;
    private BigDecimal amount;

    private LocalDateTime created_at;
}
