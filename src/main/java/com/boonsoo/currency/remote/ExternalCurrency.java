package com.boonsoo.currency.remote;


import com.boonsoo.currency.remote.dto.CurrencyResponseV1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "externalCurrency", url = "${currency.url}")
public interface ExternalCurrency {
    @GetMapping
    CurrencyResponseV1 getCurrency(@RequestParam(name = "access_key") String accessKey);
}
