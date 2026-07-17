package com.banking.bank_ledger.common.advice;

import com.banking.bank_ledger.exception.ConflictException;
import com.banking.bank_ledger.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException (ResourceNotFoundException ex) {
        ApiError apiError = ApiError.builder().message(ex.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).build();
        return handleGlobalResponse(apiError , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        List<String> errors = e.getAllErrors()
                .stream().map(message -> message.getDefaultMessage()).toList();
        ApiError apiError = ApiError.builder()
                .message("Input Validation Failed").httpStatus(HttpStatus.BAD_REQUEST)
                .subErrors(errors).build();
        return handleGlobalResponse(apiError , HttpStatus.BAD_REQUEST);
    }





//    public

}
