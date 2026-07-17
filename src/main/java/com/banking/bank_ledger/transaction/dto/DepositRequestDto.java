package com.banking.bank_ledger.transaction.dto;


import com.banking.bank_ledger.transaction.dto.type.PaymentMode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequestDto {
   private String accountNumber;
   private BigDecimal amount;
   private PaymentMode paymentMode;

}
