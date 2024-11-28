package com.sparta.currency_user.common.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

//에러처리를 공통적으로 처리하기 위해 작성한 클래스
@RestControllerAdvice
public class GlobalExceptionHandler {

    //기본적으로 많이 사용하는 ResponseStatusException 에러를 공통 처리하는 메서드
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

    // @Valid 또는 @Validated로 인한 유효성 검사 실패를 공통적으로 처리하는 메서드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handlerMethodArgumentNotValidException(
        MethodArgumentNotValidException ex, HttpServletRequest request
    ) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "유효성 검사 실패!";
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            errorMessage,
            request.getRequestURI()
        );
    }

    //에러 코드, 메시지, 경로를 받아와 ErrorResponseDto 를 생성하여 반환하는 메서드
    private ResponseEntity<ErrorResponseDto> buildErrorResponse(
        int errorCode,
        String errorMessage,
        String path
    ) {
        ErrorResponseDto responseDto = ErrorResponseDto.builder()
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .path(path)
            .build();

        return ResponseEntity.status(errorCode).body(responseDto);
    }
}
