package com.sparta.currency_user.exchange.dto;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class ExchangeGroupResponseDto {

    private final long count;
    private final BigDecimal totalAmountInKrw;

    public ExchangeGroupResponseDto(long count, BigDecimal totalAmountInKrw) {
        this.count = count;
        this.totalAmountInKrw = totalAmountInKrw;
    }
}
