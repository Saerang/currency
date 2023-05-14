package com.boonsoo.currency.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.boonsoo.currency.domain.CurrencyId.KRW;
import static com.boonsoo.currency.domain.CurrencyId.USD;
import static org.assertj.core.api.Assertions.assertThat;

class ExchangeCurrencyTest {

    @Test
    @DisplayName("ExchangeCurrency 생성")
    void test01() {
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency(KRW, USD, new BigDecimal("1342.51"));

        assertThat(exchangeCurrency).isEqualTo(new ExchangeCurrency(KRW, USD, new BigDecimal("1342.51")));
    }

    @Test
    @DisplayName("환전 금액 계산하기")
    void test02() {
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency(KRW, USD, new BigDecimal("100"));

        BigDecimal exchange = exchangeCurrency.getExchange(new BigDecimal("100"));

        assertThat(exchange).isEqualTo(new BigDecimal("10000.00"));
    }
}
