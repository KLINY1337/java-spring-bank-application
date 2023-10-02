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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue
    private int transfer_id;
    private int user_id;
    private int account_from_id;
    private String account_from_name;
    private int account_to_id;
    private String account_to_name;
    private BigDecimal amount;

    private LocalDateTime created_at;
}
