package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.CurrencyRequestDto;
import com.sparta.currency_user.dto.CurrencyResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyResponseDto findById(Long id) {
        return new CurrencyResponseDto(findCurrencyById(id));
    }

    public Currency findCurrencyById(Long id) {
        return currencyRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "통화를 찾을 수 없습니다.")
        );
    }

    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream().map(CurrencyResponseDto::toDto).toList();
    }

    @Transactional
    public CurrencyResponseDto save(CurrencyRequestDto currencyRequestDto) {
        Currency savedCurrency = currencyRepository.save(currencyRequestDto.toEntity());
        return new CurrencyResponseDto(savedCurrency);
    }

    @PostConstruct
    public void validateCurrencyRate() {
        List<Currency> currencies = currencyRepository.findAll();

        currencies.forEach(currency -> {
            BigDecimal exchangeRate = currency.getExchangeRate();
            if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
                log.error("Currency exchange rate is less than zero");
            }
        });
    }
}
