package com.boonsoo.currency.domain;

public interface CurrencyRepository {
    ExchangeCurrency exchangeCurrency(CurrencyId currencyId);
}
