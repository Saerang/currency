package com.boonsoo.currency.repository;

import com.boonsoo.currency.domain.CurrencyId;
import com.boonsoo.currency.domain.CurrencyRepository;
import com.boonsoo.currency.domain.ExchangeCurrency;
import com.boonsoo.currency.remote.ExternalCurrency;
import com.boonsoo.currency.remote.dto.CurrencyResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class MapCurrencyRepository implements CurrencyRepository {
    private final Map<CurrencyId, ExchangeCurrency> currencyMap;
    private final ExternalCurrency externalCurrency;

    @Value("${currency.access_key}")
    private String accessKey;

    @Override
    public Optional<ExchangeCurrency> findByExchangeCurrencyAndSource(CurrencyId currencyId, CurrencyId source) {
        if (!currencyMap.containsKey(currencyId)) {
            setupExternalCurrency(source);
        }

        return Optional.ofNullable(currencyMap.get(currencyId));
    }

    private void setupExternalCurrency(CurrencyId source) {
        // TODO: source 로 검색할 수 있도록 추후 수정 필요
        CurrencyResponseV1 currencyResponse = externalCurrency.getCurrency(accessKey);

        Map<String, BigDecimal> quotes = currencyResponse.getQuotes();

        List<CurrencyId> existCurrencies = quotes.keySet().stream()
                                                 .map(CurrencyId::find)
                                                 .filter(CurrencyId::existCurrency)
                                                 .toList();

        for (CurrencyId existCurrency : existCurrencies) {
            currencyMap.put(existCurrency, new ExchangeCurrency(existCurrency, source, quotes.get(existCurrency.getCurrency())));
        }
    }

    @Override
    public List<ExchangeCurrency> findAllBySource(CurrencyId source) {
        if (currencyMap == null) {
            setupExternalCurrency(source);
        }

        if (currencyMap == null) {
            return Collections.emptyList();
        }

        return currencyMap.values().stream().toList();
    }
}
