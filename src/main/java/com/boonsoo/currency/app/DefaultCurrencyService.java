package com.boonsoo.currency.app;

import com.boonsoo.currency.domain.CurrencyId;
import com.boonsoo.currency.domain.CurrencyRepository;
import com.boonsoo.currency.domain.ExchangeCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public BigDecimal getExchange(CurrencyId currencyId, BigDecimal amount) {
        ExchangeCurrency exchangeCurrency
                = currencyRepository.findExchangeCurrency(currencyId)
                                    .orElseThrow(() -> new IllegalArgumentException("Currency 가 존재하지 않습니다. currency: " + currencyId));

        return exchangeCurrency.getExchange(amount);
    }
}
