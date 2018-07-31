package com.anaadar.akaltakhatsahibpro.activities.mainMenu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Adapter.CustomPagerAdapter;
import com.anaadar.akaltakhatsahibpro.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerMenu);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
    }
}
