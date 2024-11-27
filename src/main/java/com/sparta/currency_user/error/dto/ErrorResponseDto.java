package com.sparta.currency_user.error.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDto {

    private final int errorCode;
    private final String errorMessage;
    private final String path;
}
