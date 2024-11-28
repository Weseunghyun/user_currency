package com.sparta.currency_user.exchange.entity;

import lombok.Getter;

@Getter
public enum UserCurrencyStatus {
    NORMAL("normal"),
    CANCELED("canceled");

    private final String status;

    private UserCurrencyStatus(String status) {
        this.status = status;
    }

}
