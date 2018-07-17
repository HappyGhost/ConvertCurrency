package com.myapp.convertcurrency.core.dialog;

import android.app.Dialog;

public interface DialogClickedListener {
    boolean onDialogClicked(Dialog dialog, ButtonType buttonType);
}