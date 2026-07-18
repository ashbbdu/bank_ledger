package com.banking.bank_ledger.transaction.dto;

import com.banking.bank_ledger.transaction.dto.type.PaymentMode;
import com.banking.bank_ledger.transaction.dto.type.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private PaymentMode paymentMode;
}
