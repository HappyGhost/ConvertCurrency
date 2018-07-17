package com.myapp.convertcurrency.feature.convert.dialog;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myapp.convertcurrency.R;
import com.myapp.convertcurrency.core.dialog.BaseDialogMaterialView;

import butterknife.BindView;

public class LoadingDialogMaterial extends BaseDialogMaterialView {

    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.tv_progress_message)
    protected TextView tvMessage;
    protected String mMessage;

    public LoadingDialogMaterial(Context context) {
        super(context, null);
    }

    public void setMessage(String message) {
        mMessage = message;
        if (tvMessage != null) {
            tvMessage.setText(message);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_loading_default;
    }

    @Override
    protected void bindView() {
        mProgressBar.getIndeterminateDrawable().setColorFilter(
                ContextCompat.getColor(
                        getContext(),
                        R.color.mainColor),
                PorterDuff.Mode.MULTIPLY);
        tvMessage.setText(mMessage);
    }
}
