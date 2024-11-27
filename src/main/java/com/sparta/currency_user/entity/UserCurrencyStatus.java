package com.sparta.currency_user.entity;

public enum UserCurrencyStatus {
    NORMAL("normal"),
    CANCELED("canceled");

    private final String status;

    private UserCurrencyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
