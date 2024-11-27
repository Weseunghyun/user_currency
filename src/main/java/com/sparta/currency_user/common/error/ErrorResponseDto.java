package com.sparta.currency_user.common.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDto {

    private final int errorCode;
    private final String errorMessage;
    private final String path;
}
