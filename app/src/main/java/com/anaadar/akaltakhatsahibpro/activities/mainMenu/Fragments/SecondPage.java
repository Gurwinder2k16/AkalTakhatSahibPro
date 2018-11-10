package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.PurposeOfApp;
import com.anaadar.akaltakhatsahibpro.activities.pdfpresenterapi.PDFPRESENTER;

/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class SecondPage extends Fragment implements View.OnClickListener {
    Button btOne, btTwo, btThree, btFour, btFive, btSix;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagetwo, container, false);
        btOne = view.findViewById(R.id.this_month);
        btTwo = view.findViewById(R.id.this_year);
        btThree = view.findViewById(R.id.special_days);
        btFour = view.findViewById(R.id.upcoming_event);
        btFive = view.findViewById(R.id.live_video);
        btSix = view.findViewById(R.id.purpose);
        btSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null && !getActivity().isFinishing()) {
                    startActivity(new Intent(getActivity(), PurposeOfApp.class));
                }
            }
        });
        btOne.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btThree.setOnClickListener(this);
        btFour.setOnClickListener(this);
        btFive.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_video:
                break;
            case R.id.upcoming_event:
                intentShow("http://sgpc.net/wp-content/uploads/2017/03/Jantri-NS_549.pdf ", "Sikh Days");
                break;
            case R.id.this_year:
                intentShow("http://old.sgpc.net/calendar2018.pdf", "Sikh Nanak Shahi Calender");
                break;
            case R.id.special_days:
                intentShow("http://old.sgpc.net/hukumnama/jpeg%20hukamnama/Historical%20Days_NS_550.PDF ", "Sikhism Special Days");
                break;
            case R.id.this_month:
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
