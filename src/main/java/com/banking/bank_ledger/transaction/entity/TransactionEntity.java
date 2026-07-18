package com.banking.bank_ledger.transaction.entity;

import com.banking.bank_ledger.account.entity.AccountEntity;
import com.banking.bank_ledger.transaction.dto.type.PaymentMode;
import com.banking.bank_ledger.transaction.dto.type.TransactionStatus;
import com.banking.bank_ledger.transaction.dto.type.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Column(length = 500)
    private String description;

   @CreationTimestamp
   private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//    }
}
