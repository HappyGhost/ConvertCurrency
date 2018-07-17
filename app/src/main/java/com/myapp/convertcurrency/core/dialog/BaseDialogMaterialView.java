package com.myapp.convertcurrency.core.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.myapp.convertcurrency.R;

import butterknife.ButterKnife;

public abstract class BaseDialogMaterialView {

    protected static final int NO_ANIMATION = -1;
    protected Dialog mMaterialDialog;
    protected DialogClickedListener mDialogClickedListener;
    private Context mContext;
    private View mView;

    public BaseDialogMaterialView(Context context, DialogClickedListener buttonListener) {
        mContext = context;
        mDialogClickedListener = buttonListener;
        mMaterialDialog = new Dialog(mContext, android.R.style.Theme_Translucent_NoTitleBar);
        mMaterialDialog.setCanceledOnTouchOutside(false);
        mMaterialDialog.setCancelable(false);
        mView = LayoutInflater.from(context).inflate(getLayout(), null);
        mMaterialDialog.setContentView(mView);
        ButterKnife.bind(this, mView);
        bindView();
    }

    public View getCustomView() {
        return mView;
    }

    public abstract int getLayout();

    public Dialog getDialog() {
        return mMaterialDialog;
    }

    protected abstract void bindView();

    protected View getView() {
        return mView;
    }

    protected int getDefaultAnimation() {
        return R.style.Dialog_InFade_OutFade_Animation;
    }

    protected void setAnimation(int animationResources) {
        if (animationResources != NO_ANIMATION && mMaterialDialog.getWindow() != null) {
            mMaterialDialog.getWindow().setWindowAnimations(animationResources);
        }
    }

    protected Context getContext() {
        return mContext;
    }

    public DialogClickedListener getDialogClickedListener() {
        return mDialogClickedListener;
    }
}
