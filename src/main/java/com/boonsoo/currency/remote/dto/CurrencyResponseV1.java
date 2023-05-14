package com.boonsoo.currency.remote.dto;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyResponseV1 {
    private final boolean success;
    private final String terms;
    private final String privacy;
    private final long timestemp;
    private final String source;
    private final Map<String, BigDecimal> quotes;

    public CurrencyResponseV1(boolean success, String terms, String privacy, long timestemp, String source, Map<String, BigDecimal> quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestemp = timestemp;
        this.source = source;
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "CurrencyResponseV1{" + "success=" + success + ", terms='" + terms + '\'' + ", privacy='" + privacy + '\'' + ", timestemp=" + timestemp + ", source='" + source + '\'' + ", quotes=" + quotes + '}';
    }
}
