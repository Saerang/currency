package com.boonsoo.currency.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum CurrencyId {
    KRW("KRW", "한국", 2),
    JPY("JPY", "일본", 3),
    PHP("PHP", "필리핀", 3),
    USD("USD", "미국", 1),
    UNKNOWN("", "알수없음", 0);

    private final String currency;
    private final String country;
    private final int scale;

    CurrencyId(String currency, String country, int scale) {
        this.currency = currency;
        this.country = country;
        this.scale = scale;
    }


    private static final Map<String, CurrencyId> currencies
            = Collections.unmodifiableMap(Stream.of(values())
                                                .collect(Collectors.toMap(CurrencyId::getCurrency, x -> x)));


    public static boolean existCurrency(CurrencyId currencyId) {
        return currencyId != UNKNOWN;
    }

    public static CurrencyId find(String currency) {
        return Optional.ofNullable(currencies.get(currency)).orElse(UNKNOWN);
    }
}
