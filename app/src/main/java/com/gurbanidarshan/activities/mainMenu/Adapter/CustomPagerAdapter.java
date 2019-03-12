package com.gurbanidarshan.activities.mainMenu.Adapter;

/**
 * Created by Sumit Singh on 3/12/2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gurbanidarshan.activities.mainMenu.Fragments.FirstPage;
import com.gurbanidarshan.activities.mainMenu.Fragments.FourPage;
import com.gurbanidarshan.activities.mainMenu.Fragments.InstrPage;
import com.gurbanidarshan.activities.mainMenu.Fragments.SecondPage;
import com.gurbanidarshan.activities.mainMenu.Fragments.ThirdPage;


public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;
    private FourPage fourPage;
    private InstrPage instrPage;
    public FirstPage firstPage;
    private SecondPage secondPage;
    private ThirdPage thirdPage;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fourPage = new FourPage();
        instrPage = new InstrPage();
        firstPage = new FirstPage();
        secondPage = new SecondPage();
        thirdPage = new ThirdPage();
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fourPage;
            case 1:
                return instrPage;
            case 2:
                return firstPage;
            case 3:
                return secondPage;
            case 4:
                return thirdPage;
           /* case 5:
                return new FivePage();
            case 6:
                return new SixPage();*/
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}

