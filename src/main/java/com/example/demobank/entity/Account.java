package com.example.demobank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    private int account_id;
    private int user_id;

    private String account_number;
    private String account_name;
    private String account_type;

    @Column(columnDefinition = "decimal(18,2) default 0.00")
    private BigDecimal balance;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;





}
