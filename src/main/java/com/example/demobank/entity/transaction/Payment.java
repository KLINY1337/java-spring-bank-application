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
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private int payment_id;
    private int user_from_id;
    private String user_from_name;
    private String user_from_account_name;
    private int user_to_id;
    private String user_to_email;
    private int user_to_account_id;
    private int user_to_account_number;
    private double amount;
    private String reference;

    private LocalDateTime created_at;

}
