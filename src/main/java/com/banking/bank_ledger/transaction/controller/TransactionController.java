package com.banking.bank_ledger.transaction.controller;

import com.banking.bank_ledger.transaction.dto.DepositRequestDto;
import com.banking.bank_ledger.transaction.entity.TransactionEntity;
import com.banking.bank_ledger.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping(path = "/deposit")
    public TransactionEntity deposit (@RequestBody @Validated DepositRequestDto depositRequestDto) {
        return transactionService.deposit(depositRequestDto);
    }

}
