package com.banking.bank_ledger.transaction.repository;

import com.banking.bank_ledger.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity , Long> {
}
