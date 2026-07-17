package com.banking.bank_ledger.transaction.service;

import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.account.repository.AccountRepository;
import com.banking.bank_ledger.exception.ResourceNotFoundException;
import com.banking.bank_ledger.transaction.dto.DepositRequestDto;
import com.banking.bank_ledger.transaction.dto.type.TransactionStatus;
import com.banking.bank_ledger.transaction.dto.type.TransactionType;
import com.banking.bank_ledger.transaction.entity.TransactionEntity;
import com.banking.bank_ledger.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionEntity deposit(DepositRequestDto depositRequestDto) {
       AccountEntity account = accountRepository.findByAccountNumber(depositRequestDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Invalid account !"));

       account.setBalance(account.getBalance().add(depositRequestDto.getAmount()));

       TransactionEntity transaction = new TransactionEntity();
       transaction.setTransactionId("TRAN_" + System.currentTimeMillis());
       transaction.setAccount(account);
       transaction.setType(TransactionType.DEPOSIT);
       transaction.setAmount(depositRequestDto.getAmount());
       transaction.setPaymentMode(depositRequestDto.getPaymentMode());
       transaction.setStatus(TransactionStatus.SUCCESS);

       transactionRepository.save(transaction);
       accountRepository.save(account);

       return transaction;
    }
}
