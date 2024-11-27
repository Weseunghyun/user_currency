package com.sparta.currency_user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExchangeResponseDto {

    private final Long id;
    private final Long userId;
    private final Long currencyId;
    private final BigDecimal amountInKrw;
    private final BigDecimal amountAfterExchange;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ExchangeResponseDto(Long id, Long userId, Long currencyId, BigDecimal amountInKrw,
        BigDecimal amountAfterExchange, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
