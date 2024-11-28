package com.sparta.currency_user.exchange.dto;

import com.sparta.currency_user.common.util.CurrencyUtil;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.currency.entity.CurrencyName;
import com.sparta.currency_user.exchange.entity.UserCurrency;
import com.sparta.currency_user.exchange.entity.UserCurrencyStatus;
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
    private final String formattedAmountAfterExchange;
    private final UserCurrencyStatus userCurrencyStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public ExchangeResponseDto(
        Long id, Long userId, Long currencyId,
        BigDecimal amountInKrw,
        String formattedAmountAfterExchange,
        UserCurrencyStatus userCurrencyStatus,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.formattedAmountAfterExchange = formattedAmountAfterExchange;
        this.userCurrencyStatus = userCurrencyStatus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /**
     * 환전 요청 응답 DTO 로 변환하는 메서드
     * @param userCurrency 객체
     * @return 환전 요청 응답 DTO 반환
     */
    public static ExchangeResponseDto toDto(UserCurrency userCurrency) {
        Currency currency = userCurrency.getCurrency();
        int floatingPoint = CurrencyName.getFloatingPointByCurrencyName(currency.getCurrencyName());
        BigDecimal amountAfterExchange = userCurrency.getAmountAfterExchange();

        // 포맷팅된 금액 생성
        String formattedAmount = CurrencyUtil.formatAmountAfterExchange(
            amountAfterExchange, floatingPoint, currency.getSymbol()
        );

        return ExchangeResponseDto.builder()
            .id(userCurrency.getId())
            .userId(userCurrency.getUser().getId())
            .currencyId(userCurrency.getId())
            .amountInKrw(userCurrency.getAmountInKrw())
            .formattedAmountAfterExchange(formattedAmount)
            .userCurrencyStatus(userCurrency.getStatus())
            .createdAt(userCurrency.getCreated())
            .modifiedAt(userCurrency.getModified())
            .build();
    }
}
