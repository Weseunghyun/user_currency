package com.sparta.currency_user.error;

import com.sparta.currency_user.error.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatusException(
        ResponseStatusException ex, HttpServletRequest request
    ) {
        return buildErrorResponse(
            ex.getStatusCode().value(),
            ex.getMessage(),
            request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handlerMethodArgumentNotValidException(
        MethodArgumentNotValidException ex, HttpServletRequest request
    ){
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효선 검사 실패!";
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage,
            request.getRequestURI()
        );
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponse(
        int errorCode,
        String errorMessage,
        String path
    ){
        ErrorResponseDto responseDto = ErrorResponseDto.builder()
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .path(path)
            .build();

        return ResponseEntity.status(errorCode).body(responseDto);
    }
}
