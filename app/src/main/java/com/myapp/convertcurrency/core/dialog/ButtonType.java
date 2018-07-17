package com.myapp.convertcurrency.core.dialog;

public enum ButtonType {
    POSITIVE(0), NEGATIVE(1), NEUTRAL(2), CLOSE(3);

    private int mId;

    ButtonType(int id) {
        this.mId = id;
    }

    public int getId() {
        return mId;
    }
}