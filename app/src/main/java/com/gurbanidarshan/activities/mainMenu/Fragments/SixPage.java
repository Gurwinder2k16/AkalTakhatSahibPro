package com.gurbanidarshan.activities.mainMenu.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gurbanidarshan.R;

/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class SixPage extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagesix, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}