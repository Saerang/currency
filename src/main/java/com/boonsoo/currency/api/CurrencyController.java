package com.boonsoo.currency.api;

import com.boonsoo.currency.api.dto.CurrenciesResponseV1;
import com.boonsoo.currency.app.CurrencyService;
import com.boonsoo.currency.domain.CurrencyId;
import com.boonsoo.currency.domain.ExchangeCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public CurrenciesResponseV1 currencies(@RequestParam("currencyId") String source) {
        List<ExchangeCurrency> exchangeCurrencies = currencyService.findAllBySource(CurrencyId.find(source));

        return CurrenciesResponseV1.convert(exchangeCurrencies);
    }

    @GetMapping("/currencies/{currencyId}/exchange")
    public BigDecimal exchange(@PathVariable("currencyId") String currencyId, @RequestParam String source,
                               @RequestParam BigDecimal amount) {
        return currencyService.getExchange(CurrencyId.find(currencyId),CurrencyId.find(source), amount);
    }
}
