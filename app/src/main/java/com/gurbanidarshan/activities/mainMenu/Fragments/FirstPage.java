package com.gurbanidarshan.activities.mainMenu.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.contactUS.ContactUs;
import com.gurbanidarshan.activities.dailyHukamnama.HukamnamaPDF;
import com.gurbanidarshan.activities.guru_sahiban.GuruSahibanMenus;
import com.gurbanidarshan.activities.mainMenu.side_menu_page.MenuDrawer;
import com.gurbanidarshan.activities.nitnem.BaniMenus;
import com.gurbanidarshan.activities.pdfpresenterapi.PDFPRESENTER;
import com.gurbanidarshan.constants.Constant;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class FirstPage extends Fragment implements View.OnClickListener {
    Button btOne, btTwo, btThree, btFour, btFive, btSix;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pageone, container, false);
        btOne = (Button) view.findViewById(R.id.sundar_gutka);
        btTwo = (Button) view.findViewById(R.id.hukamnaam);
        btThree = (Button) view.findViewById(R.id.Guru_Sahiban);
        btFour = (Button) view.findViewById(R.id.Library);
        btFive = (Button) view.findViewById(R.id.contactUs);
        btSix = (Button) view.findViewById(R.id.live_kirtan);
        btOne.setOnClickListener(this);
        btTwo.setOnClickListener(this);
        btThree.setOnClickListener(this);
        btFour.setOnClickListener(this);
        btFive.setOnClickListener(this);
        btSix.setOnClickListener(this);
        checkRadioIsPlay();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof MenuDrawer) {
            if (((MenuDrawer) getActivity()).checkAndPlay()) {
                initExoplayer();
                startActivity(new Intent(getActivity(), HukamnamaPDF.class));
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sundar_gutka:
                startActivity(new Intent(getActivity(), BaniMenus.class));
                break;
            case R.id.hukamnaam:
                startActivity(new Intent(getActivity(), HukamnamaPDF.class));
                break;
            case R.id.Guru_Sahiban:
                startActivity(new Intent(getActivity(), GuruSahibanMenus.class));
                break;
            case R.id.Library:
                intentShow();
                break;
            case R.id.live_kirtan:
                initExoplayer();
                break;
            case R.id.contactUs:
                startActivity(new Intent(getActivity(), ContactUs.class));
                break;
        }
    }

    private void intentShow() {
        Intent intent = new Intent(getActivity(), PDFPRESENTER.class);
        intent.putExtra("identifier", "http://new.sgpc.net/wp-content/uploads/2014/11/dpc-books-list.pdf");
        intent.putExtra("identifierName", "Sikhism Library");
        startActivity(intent);
    }

    /**
     *
     */
    public void initExoplayer() {
        if (Constant.haveNetworkConnection(getActivity())) {
            if (Constant.RadioIsplay) {
                Constant.RadioIsplay = false;
                Constant.player.stop();
                btSix.setText(R.string.live_kirtan);
            } else {
                if (Constant.player != null) {
                    Constant.player.stop();
                    btSix.setText(R.string.live_kirtan);
                }
                final String streamUrl = "http://sgpc.net:8070/live32"; //bbc world service url
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                TrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
                TrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
                DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "mediaPlayerSample"), defaultBandwidthMeter);
                MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(streamUrl), dataSourceFactory, extractorsFactory, null, null);
                Constant.player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
                Constant.player.prepare(mediaSource);
                Constant.player.setPlayWhenReady(true);
                Constant.RadioIsplay = true;
                btSix.setText(R.string.play_kirtan);
            }
        } else {
            Constant.checkNetworkConnectionWithoutExit(getActivity());
            btSix.setText(R.string.live_kirtan);
        }
    }

    void checkRadioIsPlay() {
        if (Constant.RadioIsplay) {
            btSix.setText(R.string.play_kirtan);
        } else {
            btSix.setText(R.string.live_kirtan);
        }
    }
}
