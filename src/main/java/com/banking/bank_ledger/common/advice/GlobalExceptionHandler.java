package com.banking.bank_ledger.common.advice;

import com.banking.bank_ledger.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(ConflictException.class)
//        public ResponseEntity<ApiError> handleConflictException (ConflictException ex) {
//            ApiError apiError = ApiError.builder().message(ex.getMessage()).httpStatus(HttpStatus.CONFLICT).build();
//            return new ResponseEntity<>(apiError , HttpStatus.CONFLICT);
//        }

    public ResponseEntity<ApiResponse<?>> handleGlobalResponse (ApiError apiError , HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse<>(apiError) , status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleConflictException (ConflictException ex) {
        ApiError apiError = ApiError.builder().message(ex.getMessage()).httpStatus(HttpStatus.CONFLICT).build();
        return handleGlobalResponse(apiError , HttpStatus.CONFLICT);
    }

//    public

}
