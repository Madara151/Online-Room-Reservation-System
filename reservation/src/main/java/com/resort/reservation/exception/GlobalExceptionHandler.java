package com.resort.reservation.exception;

import com.resort.reservation.dto.ApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(RuntimeException.class)
public ResponseEntity<ApiResponseDTO<String>> handleRuntime(RuntimeException ex) {
return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ex.getMessage(), null));
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponseDTO<String>> handleValidation(MethodArgumentNotValidException ex) {
String msg = ex.getBindingResult().getFieldErrors().isEmpty()
? "Validation error"
: ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();


return ResponseEntity.badRequest().body(new ApiResponseDTO<>(msg, null));
}
}