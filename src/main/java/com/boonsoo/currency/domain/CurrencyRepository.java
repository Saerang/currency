package com.boonsoo.currency.domain;

import java.util.Optional;

public interface CurrencyRepository {
    Optional<ExchangeCurrency> findExchangeCurrency(CurrencyId currencyId, CurrencyId source);
}
