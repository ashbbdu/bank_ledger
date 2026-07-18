package com.banking.bank_ledger.transaction.controller;

import com.banking.bank_ledger.transaction.dto.DepositRequestDto;
import com.banking.bank_ledger.transaction.dto.TransactionResponseDto;
import com.banking.bank_ledger.transaction.dto.WithdrawRequestDto;
import com.banking.bank_ledger.transaction.entity.TransactionEntity;
import com.banking.bank_ledger.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping(path = "/deposit")
    public TransactionResponseDto deposit (@RequestBody @Validated DepositRequestDto depositRequestDto) {
        return transactionService.deposit(depositRequestDto);
    }

    @PostMapping(path = "/withdraw")
    public TransactionResponseDto withdraw (@RequestBody @Validated WithdrawRequestDto withdrawRequestDto) {
        return transactionService.withdraw(withdrawRequestDto);
    }


    @GetMapping(path = "/list/{accountNumber}")
    public List<TransactionResponseDto> getTransactionsById (@PathVariable String accountNumber) {
        return transactionService.getTransactionsById(accountNumber);
    }



}
