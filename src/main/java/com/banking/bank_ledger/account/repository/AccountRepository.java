package com.banking.bank_ledger.account.repository;

import com.banking.bank_ledger.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity , Long> {
    public Optional<AccountEntity> findByEmail(String email);

    public Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
