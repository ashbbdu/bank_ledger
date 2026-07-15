package com.banking.bank_ledger.account.repository;

import com.banking.bank_ledger.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity , Long> {
}
