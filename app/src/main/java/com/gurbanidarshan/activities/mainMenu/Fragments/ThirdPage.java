package com.gurbanidarshan.activities.mainMenu.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.pdfpresenterapi.PDFPRESENTER;


/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class ThirdPage extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pagethree, container, false);
    }

    private void intentShow(String url, String title) {
        Intent intent = new Intent(getActivity(), PDFPRESENTER.class);
        intent.putExtra("identifier", url);
        intent.putExtra("identifierName", title);
        startActivity(intent);

    }
}
