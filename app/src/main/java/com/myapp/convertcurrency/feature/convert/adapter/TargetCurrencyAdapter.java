package com.myapp.convertcurrency.feature.convert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myapp.business.convert.info.RateInfo;
import com.myapp.convertcurrency.R;
import com.myapp.convertcurrency.core.adapter.BaseHolder;

import java.util.List;

import butterknife.BindView;

public class TargetCurrencyAdapter extends BaseAdapter {

    private List<RateInfo> mData;
    private LayoutInflater mLayoutInflater;

    public TargetCurrencyAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<RateInfo> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_currency, parent, false);
            convertView.setTag(new CurrencyHolder(convertView));
        }
        CurrencyHolder holder = (CurrencyHolder) convertView.getTag();
        holder.bind(mData.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_dropdown_currency, parent, false);
            convertView.setTag(new CurrencyHolder(convertView));
        }
        CurrencyHolder holder = (CurrencyHolder) convertView.getTag();
        holder.bind(mData.get(position));
        return convertView;
    }

    class CurrencyHolder extends BaseHolder {

        @BindView(R.id.tv_currency)
        public TextView tvCurrency;

        public CurrencyHolder(View itemView) {
            super(itemView);
        }

        public void bind(RateInfo rateInfo) {
            tvCurrency.setText(rateInfo.getCurrency());
        }
    }
}
