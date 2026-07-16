package com.banking.bank_ledger.account.controller;

import com.banking.bank_ledger.account.dto.CreateAccountDto;
import com.banking.bank_ledger.account.dto.type.AccountResponseDto;
import com.banking.bank_ledger.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(path = "/create")
    public AccountResponseDto createAccount (@RequestBody CreateAccountDto createAccountDto) {
        return accountService.createAccount(createAccountDto);
    }

    @GetMapping(path = "/{accountId}")
    public AccountResponseDto getAccountById (@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping(path = "/list")
    public List<AccountResponseDto> getAllAccounts () {
        return accountService.getAllAccounts();
    }
}
