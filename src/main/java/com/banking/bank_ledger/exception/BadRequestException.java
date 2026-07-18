package com.banking.bank_ledger.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException (String message) {
        super(message);
    }
}
