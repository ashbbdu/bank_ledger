package com.banking.bank_ledger.exception;

public class ConflictException extends RuntimeException {
   public ConflictException (String message) {
       super(message);
   }
}
