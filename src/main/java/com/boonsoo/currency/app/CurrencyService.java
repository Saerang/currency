package com.boonsoo.currency.app;

import com.boonsoo.currency.domain.CurrencyId;
import com.boonsoo.currency.domain.ExchangeCurrency;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {
    BigDecimal getExchange(CurrencyId currencyId, CurrencyId source, BigDecimal amount);

    List<ExchangeCurrency> findAllBySource(CurrencyId source);
}
