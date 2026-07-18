package com.banking.bank_ledger.transaction.dto;

import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.transaction.dto.type.PaymentMode;
import com.banking.bank_ledger.transaction.dto.type.TransactionStatus;
import com.banking.bank_ledger.transaction.dto.type.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponseDto {
    private Long id;
    private String transactionId;
//    private AccountEntity account;
    private String account;
    private TransactionType type;
    private BigDecimal amount;
    private PaymentMode paymentMode;
    private TransactionStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
