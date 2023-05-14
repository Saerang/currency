package com.boonsoo.currency.app;

import com.boonsoo.currency.domain.CurrencyId;
import com.boonsoo.currency.domain.CurrencyRepository;
import com.boonsoo.currency.domain.ExchangeCurrency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {
    @InjectMocks
    private DefaultCurrencyService currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    @DisplayName("환율 정보 가져오기")
    void test01() {
        CurrencyId currencyId = CurrencyId.KRW;
        Mockito.when(currencyRepository.findExchangeCurrency(currencyId))
               .thenReturn(Optional.of(ExchangeCurrency.usd(currencyId, BigDecimal.valueOf(1000))));

        BigDecimal exchange = currencyService.getExchange(currencyId, BigDecimal.valueOf(100));

        assertThat(exchange).isEqualTo(new BigDecimal("100000.00"));
    }

    @Test
    @DisplayName("환율 정보가 없을 때 에러 발생")
    void test02() {
        assertThatThrownBy(() -> currencyService.getExchange(CurrencyId.KRW, BigDecimal.valueOf(100)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
