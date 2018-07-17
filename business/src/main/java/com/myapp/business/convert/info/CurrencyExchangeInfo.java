package com.myapp.business.convert.info;

import java.util.List;

public class CurrencyExchangeInfo {

    private String baseCurrency;
    private List<RateInfo> rates;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public List<RateInfo> getRates() {
        return rates;
    }

    public void setRates(List<RateInfo> rates) {
        this.rates = rates;
    }
}
