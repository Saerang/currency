package com.boonsoo.currency.api.dto;

import com.boonsoo.currency.domain.ExchangeCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class CurrenciesResponseV1 {
    private List<CurrencyV1> currencies;

    public CurrenciesResponseV1(List<CurrencyV1> currencies) {
        this.currencies = currencies;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrencyV1 {
        private String currency;
        private String country;
        private String source;
        private BigDecimal exchangeRate;
    }

    public static CurrenciesResponseV1 convert(List<ExchangeCurrency> exchangeCurrencies) {
        List<CurrencyV1> currencies
                = exchangeCurrencies.stream()
                                    .map(currency -> new CurrencyV1(currency.exchangeCurrency().getCurrency(),
                                                                    currency.exchangeCurrency().getCountry(),
                                                                    currency.source().getCurrency(),
                                                                    currency.exchangeRate()))
                                    .toList();
        return new CurrenciesResponseV1(currencies);
    }
}
