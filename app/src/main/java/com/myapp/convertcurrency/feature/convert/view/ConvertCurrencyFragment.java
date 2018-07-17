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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.myapp.business.convert.info.CurrencyExchangeInfo;
import com.myapp.business.convert.info.RateInfo;
import com.myapp.convertcurrency.R;
import com.myapp.convertcurrency.feature.convert.adapter.TargetCurrencyAdapter;
import com.myapp.convertcurrency.feature.convert.dialog.LoadingDialogMaterial;
import com.myapp.convertcurrency.feature.convert.filter.MoneyValueFilter;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenter;
import com.myapp.convertcurrency.util.DateUtil;

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
    Spinner mSnTargetCurrency;
    @BindView(R.id.tv_target_amount_converted)
    TextView mTvTargetAmountConverted;
    @BindView(R.id.tv_note)
    TextView mTvNote;

    private Unbinder mUnbinder;
    private LoadingDialogMaterial mLoadingDialogMaterial;
    private TargetCurrencyAdapter mTargetCurrencyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_currency, container, false);
        mUnbinder = ButterKnife.bind(this, view);
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
        mTargetCurrencyAdapter = new TargetCurrencyAdapter(getContext());
        mSnTargetCurrency.setAdapter(mTargetCurrencyAdapter);
        mSnTargetCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onCurrencyItemSelected(position);
                mPresenter.convertCurrency(mEdtSourceAmountInput.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
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
        mTargetCurrencyAdapter.setData(info.getRates());
        mTargetCurrencyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void showTargetAmountValue(double value) {
        String result = String.format("%.2f", value);
        mTvTargetAmountConverted.setText(result);
    }

    @Override
    public void clearTargetAmountField() {
        mTvTargetAmountConverted.setText("");
    }

    @Override
    public void showCurrencyNote(CurrencyExchangeInfo info, RateInfo rateInfo) {
        String note = getString(R.string.convert_currency_note,
                info.getBaseCurrency(),
                rateInfo.getRate(),
                rateInfo.getCurrency(),
                DateUtil.convertDateFormat(rateInfo.getDate(), DateUtil.YYYY_MM_DD_FORMAT, DateUtil.MM_DD_YYYY_FORMAT));
        mTvNote.setText(note);
    }
}
