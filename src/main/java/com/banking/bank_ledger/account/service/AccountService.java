package com.banking.bank_ledger.account.service;

import com.banking.bank_ledger.account.dto.CreateAccountDto;
import com.banking.bank_ledger.account.dto.type.AccountResponseDto;
import com.banking.bank_ledger.account.dto.type.AccountStatus;
import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.account.repository.AccountRepository;
import com.banking.bank_ledger.exception.ConflictException;
import com.banking.bank_ledger.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountResponseDto createAccount(CreateAccountDto createAccountDto) {

        Optional<AccountEntity> existingAccount =  accountRepository.findByEmail(createAccountDto.getEmail());
        if (existingAccount.isPresent()) {
            throw new ConflictException("User with email " + createAccountDto.getEmail() + " already exists !");
        }

        AccountEntity account = modelMapper.map(createAccountDto , AccountEntity.class);

        String accountNumber = "ACC" + System.currentTimeMillis();
        account.setAccountNumber(accountNumber);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("0.00"));
        accountRepository.save(account);

        return modelMapper.map(account , AccountResponseDto.class);

    }

    public AccountResponseDto getAccountById(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Invalid Account"));
        return modelMapper.map(account , AccountResponseDto.class);
    }

    public List<AccountResponseDto> getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        return accounts.stream().map(res -> modelMapper.map(res , AccountResponseDto.class)).toList();
    }
}
