package com.sparta.currency_user.currency.entity;

import com.sparta.currency_user.common.entity.TimeBaseEntity;
import com.sparta.currency_user.exchange.entity.UserCurrency;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class Currency extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;

    //UserCurrency 엔티티와 연관관계 설정
    @OneToMany(mappedBy = "currency")
    List<UserCurrency> userCurrencies = new ArrayList<>();

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public Currency() {
    }
}
