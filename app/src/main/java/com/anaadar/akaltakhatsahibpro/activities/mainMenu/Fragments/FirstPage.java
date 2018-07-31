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
import com.anaadar.akaltakhatsahibpro.activities.dailyHukamnama.HukamnamaPDF;
import com.anaadar.akaltakhatsahibpro.activities.guru_sahiban.GuruSahibanMenus;
import com.anaadar.akaltakhatsahibpro.activities.nitnem.BaniMenus;
import com.anaadar.akaltakhatsahibpro.activities.nitnem.NitnemBani;
import com.anaadar.akaltakhatsahibpro.activities.pdfpresenterapi.PDFPRESENTER;


/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class FirstPage extends Fragment implements View.OnClickListener {
    LinearLayout btOne, btTwo, btThree, btFour;
    ImageButton mbtOne, mbtTwo, mbtThree, mbtFour;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pageone, container, false);
        btOne = (LinearLayout) view.findViewById(R.id.sundar_gutka);
        btTwo = (LinearLayout) view.findViewById(R.id.hukamnaam);
        btThree = (LinearLayout) view.findViewById(R.id.Guru_Sahiban);
        btFour = (LinearLayout) view.findViewById(R.id.Library);
        mbtOne = (ImageButton) view.findViewById(R.id.msundar_gutka);
        mbtTwo = (ImageButton) view.findViewById(R.id.mhukamnaam);
        mbtThree = (ImageButton) view.findViewById(R.id.mGuru_Sahiban);
        mbtFour = (ImageButton) view.findViewById(R.id.mLibrary);
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
            case R.id.sundar_gutka:
                startActivity(new Intent(getActivity(), BaniMenus.class));
                break;
            case R.id.msundar_gutka:
                startActivity(new Intent(getActivity(), BaniMenus.class));
                break;
            case R.id.hukamnaam:
                startActivity(new Intent(getActivity(), HukamnamaPDF.class));
                break;
            case R.id.mhukamnaam:
                startActivity(new Intent(getActivity(), HukamnamaPDF.class));
                break;
            case R.id.Guru_Sahiban:
                startActivity(new Intent(getActivity(), GuruSahibanMenus.class));
                break;
            case R.id.mGuru_Sahiban:
                startActivity(new Intent(getActivity(), GuruSahibanMenus.class));
                break;
            case R.id.Library:
                intentShow("http://new.sgpc.net/wp-content/uploads/2014/11/dpc-books-list.pdf", "Sikhism Library");
                break;
            case R.id.mLibrary:
                intentShow("http://new.sgpc.net/wp-content/uploads/2014/11/dpc-books-list.pdf", "Sikhism Library");
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
