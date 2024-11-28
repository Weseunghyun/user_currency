package com.sparta.currency_user.currency.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 각 통화의 출력 형식을 다르게 하기 위한 Enum 타입인 CurrencyName 을 생성
 * 각 통화 이름에 대해 소수점 자리수를 정의하고,
 * 주어진 통화 이름에 대한 소수점 자리수를 반환하는 메서드를 제공
 */
@Getter
public enum CurrencyName {
    USD(2), JPY(0), EUR(1);

    private final int floatingPoint;

    private CurrencyName(int floatingPoint) {
        this.floatingPoint = floatingPoint;
    }

    /**
     * 주어진 통화 이름에 해당하는 소수점 자리수를 반환
     *
     * @param currencyName 통화 이름 (예: "USD", "JPY", "EUR")
     * @return 해당 통화의 소수점 자리수
     * @throws ResponseStatusException 통화 이름이 유효하지 않은 경우
     */
    public static int getFloatingPointByCurrencyName(String currencyName) {
        for (CurrencyName c : CurrencyName.values()) {
            if (c.name().equals(currencyName)) {
                return c.getFloatingPoint();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found currencyName");
    }
}
