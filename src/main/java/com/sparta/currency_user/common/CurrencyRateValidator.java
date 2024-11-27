package com.sparta.currency_user.common;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyRateValidator {
    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void validateCurrencyRate(){
        List<Currency> currencies = currencyRepository.findAll();

        currencies.forEach(currency -> {
            BigDecimal exchangeRate = currency.getExchangeRate();
            if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0 ) {
                log.error("Currency exchange rate is less than zero");
            }
        });
    }
}
