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
import java.util.List;
import java.util.Optional;

import static com.boonsoo.currency.domain.CurrencyId.*;
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
        Mockito.when(currencyRepository.findByExchangeCurrencyAndSource(KRW, USD))
               .thenReturn(Optional.of(getExchangeCurrency(KRW, 1000)));

        BigDecimal exchange = currencyService.getExchange(KRW, USD, BigDecimal.valueOf(100));

        assertThat(exchange).isEqualTo(new BigDecimal("100000.00"));
    }

    @Test
    @DisplayName("환율 정보가 없을 때 에러 발생")
    void test02() {
        assertThatThrownBy(() -> currencyService.getExchange(KRW, USD, BigDecimal.valueOf(100)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("활율 수취국가 정보 목록 가져오기")
    void test03() {
        Mockito.when(currencyRepository.findAllBySource(USD))
               .thenReturn(List.of(getExchangeCurrency(KRW, 1000), getExchangeCurrency(PHP, 50)));

        List<ExchangeCurrency> exchangeCurrencies = currencyService.findAllBySource(USD);

        assertThat(exchangeCurrencies).hasSize(2)
                                      .containsExactly(new ExchangeCurrency(KRW, USD, BigDecimal.valueOf(1000)),
                                                       new ExchangeCurrency(PHP, USD, BigDecimal.valueOf(50)));
    }

    @Test
    @DisplayName("수취국가가 없으면 빈 리스트 반환")
    void test04() {
        List<ExchangeCurrency> exchangeCurrencies = currencyService.findAllBySource(USD);

        assertThat(exchangeCurrencies).isEmpty();
    }

    private static ExchangeCurrency getExchangeCurrency(CurrencyId currencyId, int amount) {
        return new ExchangeCurrency(currencyId, USD, BigDecimal.valueOf(amount));
    }
}
