package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class CurrencyResponseDto {

    private final Long id;
    private final String currencyName;
    private final BigDecimal exchangeRate;
    private final String symbol;

    public CurrencyResponseDto(Currency currency) {
        this.id = currency.getId();
        this.currencyName = currency.getCurrencyName();
        this.exchangeRate = currency.getExchangeRate();
        this.symbol = currency.getSymbol();
    }

    public CurrencyResponseDto(Long id, String currencyName, BigDecimal exchangeRate,
        String symbol) {
        this.id = id;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    /**
     * 통화 데이터 생성 응답 DTO 변환 메서드
     * @param currency 객체
     * @return 응답 DTO
     */
    public static CurrencyResponseDto toDto(Currency currency) {
        return new CurrencyResponseDto(
            currency.getId(),
            currency.getCurrencyName(),
            currency.getExchangeRate(),
            currency.getSymbol()
        );
    }
}
