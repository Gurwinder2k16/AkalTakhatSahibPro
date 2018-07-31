package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Model;

import com.anaadar.akaltakhatsahibpro.R;

/**
 * Created by Sumit Singh on 3/12/2018.
 */

public enum pagerModel {

    PAGEPONE(R.string.pageOne, R.layout.pageone),
    PAGETWO(R.string.pageTrwo, R.layout.pagetwo),
    PAGETHREE(R.string.pageThree, R.layout.pagethree),
    PAGEFOUR(R.string.pageFour, R.layout.pagefour),
    PAGEFIVE(R.string.pageFive, R.layout.pagefive),
    PAGESIX(R.string.pageSix, R.layout.pagesix);

    private int mTitleResId;
    private int mLayoutResId;

    pagerModel(int titleResId, int layoutResId) {
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