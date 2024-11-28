package com.sparta.currency_user.exchange.service;

import static com.sparta.currency_user.common.util.CurrencyUtil.calculateExchangeAmount;
import com.sparta.currency_user.common.util.CurrencyUtil;
import com.sparta.currency_user.exchange.dto.ExchangeGroupResponseDto;
import com.sparta.currency_user.exchange.dto.ExchangeResponseDto;
import com.sparta.currency_user.currency.entity.Currency;
import com.sparta.currency_user.currency.entity.CurrencyName;
import com.sparta.currency_user.user.entity.User;
import com.sparta.currency_user.exchange.entity.UserCurrency;
import com.sparta.currency_user.exchange.entity.UserCurrencyStatus;
import com.sparta.currency_user.exchange.repository.ExchangeRepository;
import com.sparta.currency_user.currency.service.CurrencyService;
import com.sparta.currency_user.user.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final UserService userService;
    private final CurrencyService currencyService;
    private final ExchangeRepository exchangeRepository;

    public ExchangeResponseDto createExchangeRequest(
        Long userId,
        Long currencyId,
        BigDecimal amount
    ) {
        User findUser = userService.findUserById(userId);
        Currency findCurrency = currencyService.findCurrencyById(currencyId);

        /*
         * Currency 객체 내부의 currencyName 을 문자열로 받아와
         * CurrencyName enum 타입에서 해당 이름에 대한 소수점 자리수를 가져오는 과정
         */
        int floatingPoint = CurrencyName.getFloatingPointByCurrencyName(findCurrency.getCurrencyName());

        BigDecimal exchangeRate = findCurrency.getExchangeRate();

        // 환율을 기준으로 금액을 변환하는 과정
        BigDecimal amountAfterExchange = calculateExchangeAmount(amount, exchangeRate, floatingPoint);

        UserCurrency userCurrency = new UserCurrency(
            findUser, findCurrency, amount, amountAfterExchange, UserCurrencyStatus.NORMAL
        );

        UserCurrency savedUserCurrency = exchangeRepository.save(userCurrency);

        return buildExchangeResponseDto(savedUserCurrency);
    }

    public List<ExchangeResponseDto> findExchangeRequests(Long userId) {
        // 사용자 ID와 상태 = NORMAL 에 따라 모든 환전 요청을 조회
        return exchangeRepository.findAllByUserIdAndStatus(userId, UserCurrencyStatus.NORMAL)
            .stream().map(ExchangeResponseDto::toDto).toList();
    }

    public ExchangeResponseDto updateExchangeRequest(Long userCurrencyId) {
        UserCurrency findUserCurrency = exchangeRepository.findByIdOrElseThrow(userCurrencyId);

        // 사용자 통화 상태를 CANCELED로 업데이트
        findUserCurrency.updateUserStatus(UserCurrencyStatus.CANCELED);

        UserCurrency savedUserCurrency = exchangeRepository.save(findUserCurrency);

        return buildExchangeResponseDto(savedUserCurrency);
    }

    public ExchangeGroupResponseDto findGroupExchangeRequest(Long id) {
        // 사용자 ID에 상태 = NORMAL 에 따라 그룹 환전 요청을 조회합니다.
        return exchangeRepository.findGroupExchangeRequestByUserId(id, UserCurrencyStatus.NORMAL);
    }

    /**
     * builder 를 사용하는 부분이 유사함을 발견, 객체 -> DTO 변환은 서비스 계층에서 하는게 맞다고 판단하여
     * private 메서드로 통합
     * @param userCurrency 응답 DTO 생성을 위한 UserCurrency 객체
     * @return 생성된 ExchangeResponseDto 반환
     */
    private ExchangeResponseDto buildExchangeResponseDto(UserCurrency userCurrency) {
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
            .currencyId(userCurrency.getCurrency().getId())
            .amountInKrw(userCurrency.getAmountInKrw())
            .formattedAmountAfterExchange(formattedAmount) // 추가
            .userCurrencyStatus(userCurrency.getStatus())
            .createdAt(userCurrency.getCreated())
            .modifiedAt(userCurrency.getModified())
            .build();
    }
}
