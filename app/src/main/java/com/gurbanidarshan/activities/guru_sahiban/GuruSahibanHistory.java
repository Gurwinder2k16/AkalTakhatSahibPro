package com.gurbanidarshan.activities.guru_sahiban;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.guru_sahiban.pageAdapter.CustomPagerAdapter;
import com.gurbanidarshan.activities.guru_sahiban.pageAdapter.DepthPageTransformer;


public class GuruSahibanHistory extends AppCompatActivity {

    int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nitnem_bani);
        mIndex = getIntent().getExtras().getInt("GuruSahibMessage");
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this, mIndex));
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}