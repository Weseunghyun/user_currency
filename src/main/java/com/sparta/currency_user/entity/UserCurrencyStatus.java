package com.sparta.currency_user.entity;

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
