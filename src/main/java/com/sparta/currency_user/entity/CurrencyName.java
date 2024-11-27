package com.sparta.currency_user.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum CurrencyName {
    USD(2), JPY(0), EUR(1);

    private final int floatingPoint;

    private CurrencyName(int floatingPoint) {
        this.floatingPoint = floatingPoint;
    }

    public int getFloatingPoint() {
        return floatingPoint;
    }

    public static int getFloatingPointByCurrencyName(String currencyName) {
        for (CurrencyName c : CurrencyName.values()) {
            if (c.name().equals(currencyName)) {
                return c.getFloatingPoint();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found currencyName");
    }
}
