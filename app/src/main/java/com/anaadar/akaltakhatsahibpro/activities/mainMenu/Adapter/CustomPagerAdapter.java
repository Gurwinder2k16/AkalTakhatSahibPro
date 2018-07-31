package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Adapter;

/**
 * Created by Sumit Singh on 3/12/2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.FirstPage;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.FivePage;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.FourPage;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.InstrPage;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.SecondPage;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.SixPage;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments.ThirdPage;


public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 7;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FourPage();
            case 1:
                return new InstrPage();
            case 2:
                return new FirstPage();
            case 3:
                return new SecondPage();
            case 4:
                return new ThirdPage();
            case 5:
                return new FivePage();
            case 6:
                return new SixPage();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}

