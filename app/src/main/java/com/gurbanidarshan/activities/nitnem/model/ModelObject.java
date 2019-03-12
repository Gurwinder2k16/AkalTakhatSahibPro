package com.gurbanidarshan.activities.nitnem.model;

import com.gurbanidarshan.R;

public enum ModelObject {
    RED(1, R.layout.layout_blue),
    BLUE(2, R.layout.layout_blue),
    GREEN(3, R.layout.layout_blue);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}