package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.pdfpresenterapi.PDFPRESENTER;


/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class ThirdPage extends Fragment implements View.OnClickListener {
    LinearLayout btOne, btTwo, btThree, btFour;
    ImageButton mbtOne, mbtTwo, mbtThree, mbtFour;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagethree, container, false);
        btOne = (LinearLayout) view.findViewById(R.id.this_month);
        btTwo = (LinearLayout) view.findViewById(R.id.this_year);
        btThree = (LinearLayout) view.findViewById(R.id.special_days);
        btFour = (LinearLayout) view.findViewById(R.id.upcoming_event);
        mbtOne = (ImageButton) view.findViewById(R.id.mthis_month);
        mbtTwo = (ImageButton) view.findViewById(R.id.mthis_year);
        mbtThree = (ImageButton) view.findViewById(R.id.mspecial_days);
        mbtFour = (ImageButton) view.findViewById(R.id.mupcoming_event);
        btOne.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btThree.setOnClickListener(this);
        btFour.setOnClickListener(this);
        mbtOne.setOnClickListener(this);
        mbtTwo.setOnClickListener(this);
        mbtThree.setOnClickListener(this);
        mbtFour.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upcoming_event:
                intentShow("http://sgpc.net/wp-content/uploads/2017/03/Jantri-NS_549.pdf ", "Sikh Days");
                break;
            case R.id.mupcoming_event:
                intentShow("http://sgpc.net/wp-content/uploads/2017/03/Jantri-NS_549.pdf ", "Sikh Days");
                break;
            case R.id.this_year:
                intentShow("http://old.sgpc.net/calendar2018.pdf", "Sikh Nanak Shahi Calender");
                break;
            case R.id.mthis_year:
                intentShow("http://old.sgpc.net/calendar2018.pdf", "Sikh Nanak Shahi Calender");
                break;
            case R.id.special_days:
                intentShow("http://old.sgpc.net/hukumnama/jpeg%20hukamnama/Historical%20Days_NS_550.PDF ", "Sikhism Special Days");
                break;
            case R.id.mspecial_days:
                intentShow("http://old.sgpc.net/hukumnama/jpeg%20hukamnama/Historical%20Days_NS_550.PDF ", "Sikhism Special Days");
                break;
            case R.id.this_month:
                intentShow("http://old.sgpc.net/Ragi%20List_pun.pdf", "Ragi's Duties in Harimandir Sahib");
                break;
            case R.id.mthis_month:
                intentShow("http://old.sgpc.net/Ragi%20List_pun.pdf", "Ragi's Duties in Harimandir Sahib");
                break;
        }
    }

    private void intentShow(String url, String title) {
        Intent intent = new Intent(getActivity(), PDFPRESENTER.class);
        intent.putExtra("identifier", url);
        intent.putExtra("identifierName", title);
        startActivity(intent);

    }
}
