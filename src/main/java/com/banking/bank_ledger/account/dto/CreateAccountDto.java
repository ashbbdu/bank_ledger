package com.banking.bank_ledger.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAccountDto {
    @NotBlank(message = "Email is required")
    @NotNull(message = "Email should not be empty")
    private String email;
    @NotBlank(message = "Customer Name is required")
    @NotNull(message = "Customer Name should not be empty")
    private String customerName;
}
