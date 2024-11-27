package com.sparta.currency_user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.Getter;

@Entity
@Getter
public class UserCurrency extends TimeBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_currency_id", nullable = false)
    private Currency currency;

    private BigDecimal amountInKrw;
    private BigDecimal amountAfterExchange;

    @Enumerated(EnumType.STRING)
    private UserCurrencyStatus status;

    public UserCurrency(User user, Currency currency, BigDecimal amountInKrw,
        BigDecimal amountAfterExchange, UserCurrencyStatus status) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public UserCurrency() {

    }

    public void updateUserStatus(UserCurrencyStatus newStatus) {
        this.status = newStatus;
    }
}
