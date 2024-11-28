package com.sparta.currency_user.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyUtil {

    public static BigDecimal calculateExchangeAmount(
        BigDecimal amount,
        BigDecimal rate,
        int floatingPoint
    ) {
        return amount.divide(rate, floatingPoint, RoundingMode.HALF_UP);
    }

    public static String formatAmountAfterExchange(
        BigDecimal amountAfterExchange,
        int floatingPoint,
        String symbol
    ) {
        return amountAfterExchange.setScale(floatingPoint, RoundingMode.HALF_UP)
            .toPlainString() + " " + symbol;
    }

}
