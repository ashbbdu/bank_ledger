package com.banking.bank_ledger.transaction.service;

import com.banking.bank_ledger.account.dto.type.AccountStatus;
import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.account.repository.AccountRepository;
import com.banking.bank_ledger.exception.BadRequestException;
import com.banking.bank_ledger.exception.ConflictException;
import com.banking.bank_ledger.exception.ResourceNotFoundException;
import com.banking.bank_ledger.transaction.dto.*;
import com.banking.bank_ledger.transaction.dto.type.TransactionStatus;
import com.banking.bank_ledger.transaction.dto.type.TransactionType;
import com.banking.bank_ledger.transaction.entity.TransactionEntity;
import com.banking.bank_ledger.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    Logger log = LoggerFactory.getLogger(TransactionService.class);

    @Transactional
    public TransactionResponseDto deposit(DepositRequestDto depositRequestDto) {
       AccountEntity account = accountRepository.findByAccountNumber(depositRequestDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Invalid account !"));

       if(depositRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0 ) {
           throw new BadRequestException("Amount must be greater than zero");
       }

        if(account.getStatus() != AccountStatus.ACTIVE){
            throw new ConflictException("Account is not active");
        }

       account.setBalance(account.getBalance().add(depositRequestDto.getAmount()));

       TransactionEntity transaction = new TransactionEntity();
       transaction.setTransactionId("TRAN_" + System.currentTimeMillis());
       transaction.setAccount(account);
       transaction.setType(TransactionType.DEPOSIT);
       transaction.setAmount(depositRequestDto.getAmount());
       transaction.setPaymentMode(depositRequestDto.getPaymentMode());
       transaction.setStatus(TransactionStatus.SUCCESS);

       accountRepository.save(account);
       transactionRepository.save(transaction);
       log.info("Deposit of {} to account {}", depositRequestDto.getAmount(), account.getAccountNumber());
       return modelMapper.map(transaction , TransactionResponseDto.class);
    }

    @Transactional
    public TransactionResponseDto withdraw(WithdrawRequestDto withdrawRequestDto) {
        AccountEntity account = accountRepository.findByAccountNumber(withdrawRequestDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Invalid account !"));

        if(withdrawRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new BadRequestException("Amount must be greater than zero");
        }

        if(account.getStatus() != AccountStatus.ACTIVE){
            throw new ConflictException("Account is not active");
        }

        account.setBalance(account.getBalance().subtract(withdrawRequestDto.getAmount()));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionId("TRAN_" + System.currentTimeMillis());
        transaction.setAccount(account);
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setAmount(withdrawRequestDto.getAmount());
        transaction.setPaymentMode(withdrawRequestDto.getPaymentMode());
        transaction.setStatus(TransactionStatus.SUCCESS);

        accountRepository.save(account);
        transactionRepository.save(transaction);
        log.info("Withdraw of {} to account {}", withdrawRequestDto.getAmount(), account.getAccountNumber());
        return modelMapper.map(transaction , TransactionResponseDto.class);
    }


    @Transactional
    public TransferResponseDto transfer (TransferRequestDto transferRequestDto) {
        AccountEntity senderAccount = accountRepository.findByAccountNumber(transferRequestDto.getFromAccount()).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Sender Account !"));

        AccountEntity receiverAccount = accountRepository.findByAccountNumber(transferRequestDto.getToAccount()).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Sender Account !"));


        if(transferRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Transfer Amount must be greater than zero");
        }

        if(senderAccount.getBalance().compareTo(transferRequestDto.getAmount()) < 0) {
            throw new BadRequestException("Insufficient balance !");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(transferRequestDto.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transferRequestDto.getAmount()));

        TransactionEntity senderTxn = new TransactionEntity();
        senderTxn.setTransactionId("TRAN_" + System.currentTimeMillis());
        senderTxn.setAccount(senderAccount);
        senderTxn.setType(TransactionType.TRANSFER);
        senderTxn.setAmount(transferRequestDto.getAmount());
        senderTxn.setPaymentMode(transferRequestDto.getPaymentMode());
        senderTxn.setStatus(TransactionStatus.SUCCESS);
        senderTxn.setDescription("Transferred to " + receiverAccount.getAccountNumber());

        TransactionEntity receiverTxn = new TransactionEntity();
        receiverTxn.setTransactionId("TRAN_" + (System.currentTimeMillis() + 1));
        receiverTxn.setAccount(receiverAccount);
        receiverTxn.setType(TransactionType.TRANSFER);
        receiverTxn.setAmount(transferRequestDto.getAmount());
        receiverTxn.setPaymentMode(transferRequestDto.getPaymentMode());
        receiverTxn.setStatus(TransactionStatus.SUCCESS);
        receiverTxn.setDescription("Received from " + senderAccount.getAccountNumber());



        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        transactionRepository.save(senderTxn);
        transactionRepository.save(receiverTxn);

        TransferResponseDto transferResponseDto = modelMapper.map(senderTxn , TransferResponseDto.class);
        transferResponseDto.setFromAccount(senderAccount.getAccountNumber());
        transferResponseDto.setToAccount(receiverAccount.getAccountNumber());

        return transferResponseDto;

    }



    public List<TransactionResponseDto> getTransactionsById(String accountNumber) {
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()
                -> new ResourceNotFoundException("Invalid account !"));


        List<TransactionEntity> transactions = transactionRepository.findAllByAccount(account); // here we have to pass AccountEntity or check repo
        return transactions.stream().map(res -> modelMapper.map(res , TransactionResponseDto.class)).toList();

//        both will use two queries

//        List<TransactionEntity> transactions = transactionRepository.findAllByAccountAccountNumber(accountNumber);
//
//        if(transactions.isEmpty()) {
//            throw new ResourceNotFoundException("Invalid account!");
//        }
//
//        return transactions.stream()
//                .map(transaction -> modelMapper.map(transaction, TransactionResponseDto.class))
//                .toList();

    }
}
