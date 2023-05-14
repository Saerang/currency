package com.boonsoo.currency.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record ExchangeCurrency(CurrencyId exchangeCurrency, CurrencyId source, BigDecimal exchangeRate) {

    public BigDecimal getExchange(BigDecimal amount) {
        return amount.multiply(this.exchangeRate).setScale(this.exchangeCurrency.getScale(), RoundingMode.FLOOR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeCurrency that = (ExchangeCurrency) o;
        return exchangeCurrency == that.exchangeCurrency && source == that.source && Objects.equals(exchangeRate, that.exchangeRate);
    }
}
