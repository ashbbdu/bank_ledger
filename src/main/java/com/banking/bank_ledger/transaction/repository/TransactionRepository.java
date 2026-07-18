package com.banking.bank_ledger.transaction.repository;

import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity , Long> {
//    List<TransactionEntity> findAllByAccountAccountNumber(String accountNumber);
// or use above two

    public List<TransactionEntity> findAllByAccount(AccountEntity account);





}
