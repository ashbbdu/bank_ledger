package com.banking.bank_ledger.transaction.dto;

import com.banking.bank_ledger.transaction.dto.type.PaymentMode;
import com.banking.bank_ledger.transaction.dto.type.TransactionStatus;
import com.banking.bank_ledger.transaction.dto.type.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferResponseDto {
    private Long id;
    private String transactionId;
    private String fromAccount;
    private String toAccount;
    private TransactionType type;
    private BigDecimal amount;
    private PaymentMode paymentMode;
    private TransactionStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
