package com.banking.bank_ledger.transaction.dto;

import com.banking.bank_ledger.transaction.dto.type.PaymentMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequestDto {
    @NotBlank
    private String accountNumber;

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull
    private PaymentMode paymentMode;

    private String description;
}
