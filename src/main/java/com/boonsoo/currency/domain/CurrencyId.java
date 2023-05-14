package com.boonsoo.currency.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CurrencyId {
    KRW("KRW", 2),
    JPY("JPY", 3),
    PHP("PHP", 3),
    USD("USD", 1),
    UNKNOWN("", 0);

    private final String currency;
    private final int scale;

    CurrencyId(String currency, int scale) {
        this.currency = currency;
        this.scale = scale;
    }


    private static final Map<String, CurrencyId> currencies
            = Collections.unmodifiableMap(Stream.of(values())
                                                .collect(Collectors.toMap(CurrencyId::getCurrency, x -> x)));


    public String getCurrency() {
        return currency;
    }


    public int getScale() {
        return scale;
    }


    public static boolean existCurrency(CurrencyId currencyId) {
        return currencyId != UNKNOWN;
    }

    public static CurrencyId find(String currency) {
        return Optional.ofNullable(currencies.get(currency)).orElse(UNKNOWN);
    }
}
