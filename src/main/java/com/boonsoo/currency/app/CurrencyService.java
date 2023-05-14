package com.boonsoo.currency.app;

import com.boonsoo.currency.domain.CurrencyId;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal getExchange(CurrencyId currencyId, BigDecimal amount);
}
