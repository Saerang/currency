package com.boonsoo.currency.remote;

import com.boonsoo.currency.remote.dto.CurrencyResponseV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
//@Disabled
class ExternalCurrencyTest {

    @Autowired
    private ExternalCurrency externalCurrency;

    @Test
    @DisplayName("feign test")
    void test01() {
        CurrencyResponseV1 responseV1 = externalCurrency.getCurrency("ee50cd7cc73c9b7a7bb3d9617cfb6b9c");
        System.out.println(responseV1);
    }
}
