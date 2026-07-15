package com.banking.bank_ledger.account.service;

import com.banking.bank_ledger.account.dto.CreateAccountDto;
import com.banking.bank_ledger.account.dto.type.AccountResponseDto;
import com.banking.bank_ledger.account.dto.type.AccountStatus;
import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountResponseDto createAccount(CreateAccountDto createAccountDto) {
        AccountEntity account = modelMapper.map(createAccountDto , AccountEntity.class);

        String accountNumber = "ACC" + System.currentTimeMillis();
        account.setAccountNumber(accountNumber);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("0.00"));
        accountRepository.save(account);

        return modelMapper.map(account , AccountResponseDto.class);

    }
}
