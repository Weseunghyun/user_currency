package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.entity.UserCurrencyStatus;
import com.sparta.currency_user.repository.ExchangeRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final UserService userService;
    private final CurrencyService currencyService;
    private final ExchangeRepository exchangeRepository;

    public ExchangeResponseDto createExchangeRequest(Long userId, Long currencyId, BigDecimal amount) {
        User findUser = userService.findUserById(userId);
        Currency findCurrency = currencyService.findCurrencyById(currencyId);

        BigDecimal exchangeRate = findCurrency.getExchangeRate();
        BigDecimal amountAfterExchange = amount.divide(exchangeRate,2, RoundingMode.HALF_UP);

        UserCurrency userCurrency = new UserCurrency(
            findUser,findCurrency,amount,amountAfterExchange, UserCurrencyStatus.NORMAL
        );

        UserCurrency savedUserCurrency = exchangeRepository.save(userCurrency);

        return ExchangeResponseDto.builder()
            .id(savedUserCurrency.getId())
            .userId(userId)
            .currencyId(currencyId)
            .amountInKrw(amount)
            .amountAfterExchange(amountAfterExchange)
            .createdAt(savedUserCurrency.getCreated())
            .modifiedAt(savedUserCurrency.getModified())
            .build();
    }

}
