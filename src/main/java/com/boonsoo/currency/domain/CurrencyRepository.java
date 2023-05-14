package com.boonsoo.currency.domain;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    Optional<ExchangeCurrency> findByExchangeCurrencyAndSource(CurrencyId exchangeCurrency, CurrencyId source);

    List<ExchangeCurrency> findAllBySource(CurrencyId source);
}
