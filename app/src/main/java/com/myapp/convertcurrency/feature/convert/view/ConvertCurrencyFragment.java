package com.myapp.convertcurrency.feature.convert.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.info.RateInfo;
import com.myapp.convertcurrency.R;
import com.myapp.convertcurrency.feature.convert.dialog.LoadingDialogMaterial;
import com.myapp.convertcurrency.feature.convert.filter.MoneyValueFilter;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class ConvertCurrencyFragment extends Fragment implements ConvertCurrencyView {

    @Inject
    ConvertCurrencyPresenter mPresenter;

    @BindView(R.id.tv_source_currency)
    TextView mTvSourceCurrency;
    @BindView(R.id.edt_source_amount_input)
    EditText mEdtSourceAmountInput;
    @BindView(R.id.tv_target_currency)
    TextView mTvTargetCurrency;
    @BindView(R.id.tv_target_amount_converted)
    TextView mTvTargetAmountConverted;
    @BindView(R.id.tv_note)
    TextView tvNote;

    Unbinder unbinder;
    LoadingDialogMaterial mLoadingDialogMaterial;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_currency, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter.loadCurrencyRate();
    }

    private void initView() {
        mEdtSourceAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.convertCurrency(s.toString());
            }
        });
        mEdtSourceAmountInput.setFilters(new InputFilter[]{new MoneyValueFilter()});
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProcessDialog() {
        if (mLoadingDialogMaterial == null) {
            mLoadingDialogMaterial = new LoadingDialogMaterial(getActivity());
        }
        mLoadingDialogMaterial.getDialog().show();
    }

    @Override
    public void hideProcessDialog() {
        if (mLoadingDialogMaterial != null) {
            mLoadingDialogMaterial.getDialog().dismiss();
        }
    }

    @Override
    public void showSourceData(CurrencyExchangeInfo info) {
        mTvSourceCurrency.setText(info.getBaseCurrency());

    }

    @Override
    public void showTargetCurrency(RateInfo rateInfo) {
        mTvTargetCurrency.setText(rateInfo.getCurrency());
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void showTargetAmountValue(double value) {
        String result = String.format("%.2f", value);
        mTvTargetAmountConverted.setText(result);
    }
}
