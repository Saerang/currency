package com.boonsoo.currency.repository;

import com.boonsoo.currency.domain.CurrencyId;
import com.boonsoo.currency.domain.CurrencyRepository;
import com.boonsoo.currency.domain.ExchangeCurrency;
import com.boonsoo.currency.remote.ExternalCurrency;
import com.boonsoo.currency.remote.dto.CurrencyResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MapCurrencyRepository implements CurrencyRepository {
    private final Map<CurrencyId, ExchangeCurrency> currencyMap;
    private final ExternalCurrency externalCurrency;

    @Value("${currency.access_key}")
    private String accessKey;

    @Override
    public ExchangeCurrency exchangeCurrency(CurrencyId currencyId) {
        if (!currencyMap.containsKey(currencyId)) {
            setupExternalCurrency();
        }

        return currencyMap.get(currencyId);
    }

    private void setupExternalCurrency() {
        CurrencyResponseV1 currencyResponse = externalCurrency.getCurrency(accessKey);

        Map<String, BigDecimal> quotes = currencyResponse.getQuotes();

        List<CurrencyId> existCurrencies = quotes.keySet().stream()
                                                 .map(CurrencyId::find)
                                                 .filter(CurrencyId::existCurrency)
                                                 .toList();

        for (CurrencyId existCurrency : existCurrencies) {
            currencyMap.put(existCurrency, ExchangeCurrency.usd(existCurrency, quotes.get(existCurrency.getCurrency())));
        }
    }
}
