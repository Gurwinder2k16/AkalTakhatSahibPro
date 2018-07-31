package com.anaadar.akaltakhatsahibpro.activities.guru_sahiban.pageAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anaadar.akaltakhatsahibpro.R;


public class CustomPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int mIndex = -1;

    public CustomPagerAdapter(Context context, int pmIndex) {
        mContext = context;
        mIndex = pmIndex;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_guru_sahiban, collection, false);
        //-----------------------
        TextView gurusahiban_title = (TextView) layout.findViewById(R.id.Guru_SahibanTitle);
        TextView gurusahiban_detail = (TextView) layout.findViewById(R.id.Guru_SahibanDetail);
        if (gurusahiban_title.getText().toString().isEmpty()) {
            gurusahiban_title.setText(mContext.getResources().getStringArray(R.array.GuruSahiban_Menu)[mIndex]);
        }
        if (gurusahiban_detail.getText().toString().isEmpty()) {
            gurusahiban_detail.setText(mContext.getResources().getStringArray(R.array.GuruSahibanHistory_detail)[mIndex]);
        }
       /* Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(), "fonts/amrlipi_.ttf");
        textView.setTypeface(custom_font);*/
        //---------------
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}