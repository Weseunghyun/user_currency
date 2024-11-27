package com.sparta.currency_user.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ExchangeRequestDto {

    @NotBlank(message = "userId는 필수값 입니다. 추가해주세요")
    private final Long userId;

    @NotBlank(message = "currencyId는 필수값 입니다. 추가해주세요")
    private final Long currencyId;

    @NotBlank(message = "환전 금액은 필수값 입니다. 숫자 형태로 입력해주세요")
    private final BigDecimal amountInKrw;

    public ExchangeRequestDto(Long userId, Long currencyId, BigDecimal amountInKrw) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
    }
}
