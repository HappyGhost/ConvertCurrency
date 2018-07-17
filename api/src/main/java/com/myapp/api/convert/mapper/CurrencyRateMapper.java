package com.myapp.api.convert.mapper;

import com.myapp.api.convert.model.CurrencyExchangeModel;
import com.myapp.api.core.mapper.BaseInfoMapper;
import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.info.RateInfo;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRateMapper extends BaseInfoMapper<CurrencyExchangeInfo, CurrencyExchangeModel> {
    @Override
    public CurrencyExchangeInfo transform(CurrencyExchangeModel model) {
        CurrencyExchangeInfo info = new CurrencyExchangeInfo();
        info.setBaseCurrency(model.getBaseCurrency());
        List<String> keys = new ArrayList<>(model.getRates().keySet());
        List<Double> values = new ArrayList<>(model.getRates().values());
        List<RateInfo> rates = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            RateInfo rateInfo = new RateInfo();
            rateInfo.setCurrency(keys.get(i));
            rateInfo.setRate(values.get(i));
            rateInfo.setDate(model.getDate());
            rates.add(rateInfo);
        }
        info.setRates(rates);
        return info;
    }
}
