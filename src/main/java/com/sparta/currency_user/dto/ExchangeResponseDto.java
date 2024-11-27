package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.entity.UserCurrencyStatus;
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
    private final UserCurrencyStatus userCurrencyStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ExchangeResponseDto(
        Long id, Long userId, Long currencyId,
        BigDecimal amountInKrw,
        BigDecimal amountAfterExchange,
        UserCurrencyStatus userCurrencyStatus,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.userCurrencyStatus = userCurrencyStatus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ExchangeResponseDto toDto(UserCurrency userCurrency) {
        return new ExchangeResponseDto(
            userCurrency.getId(),
            userCurrency.getUser().getId(),
            userCurrency.getCurrency().getId(),
            userCurrency.getAmountInKrw(),
            userCurrency.getAmountAfterExchange(),
            userCurrency.getStatus(),
            userCurrency.getCreated(),
            userCurrency.getModified()
        );
    }
}
