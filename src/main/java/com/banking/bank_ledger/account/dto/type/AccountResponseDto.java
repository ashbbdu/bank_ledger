package com.banking.bank_ledger.account.dto.type;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private String customerName;
    private BigDecimal balance;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
