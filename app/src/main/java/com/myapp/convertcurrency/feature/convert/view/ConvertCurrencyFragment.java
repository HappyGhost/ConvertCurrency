package com.myapp.convertcurrency.feature.convert.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.convertcurrency.R;
import com.myapp.convertcurrency.feature.convert.presenter.ConvertCurrencyPresenter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ConvertCurrencyFragment extends Fragment implements ConvertCurrencyView {

    @Inject
    ConvertCurrencyPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_convert_currency, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }
}
