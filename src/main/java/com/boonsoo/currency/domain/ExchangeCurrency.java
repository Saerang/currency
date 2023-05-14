package com.boonsoo.currency.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ExchangeCurrency {
    private final CurrencyId exchangeCurrency;
    private final CurrencyId source;
    private final BigDecimal exchangeRate;

    public ExchangeCurrency(CurrencyId exchangeCurrency, CurrencyId source, BigDecimal exchangeRate) {
        this.exchangeCurrency = exchangeCurrency;
        this.source = source;
        this.exchangeRate = exchangeRate;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(exchangeCurrency, source, exchangeRate);
    }
}
