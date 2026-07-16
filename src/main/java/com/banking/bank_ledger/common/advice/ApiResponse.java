package com.banking.bank_ledger.common.advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T>{
    private LocalDateTime timeStamp;
    private String message;
    private Boolean success;
    private ApiError error;
    private T data;

    public ApiResponse () {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse (T data) {
        this();
        this.success = true;
        this.message = "Successful Operation";
        this.data = data;
    }

    public ApiResponse (ApiError apiError) {
        this();
        this.success = false;
        this.message = "Something went wrong";
        this.error = apiError;
    }

}
