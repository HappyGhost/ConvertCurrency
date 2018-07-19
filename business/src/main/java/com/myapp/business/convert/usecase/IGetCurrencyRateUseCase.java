package com.myapp.business.convert.usecase;

import com.myapp.business.convert.callback.GetCurrencyCallBack;
import com.myapp.business.core.usecase.IUseCase;

public interface IGetCurrencyRateUseCase extends IUseCase<GetCurrencyCallBack> {
    IGetCurrencyRateUseCase buildUseCase();
}
