package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.contactUS.ContactUs;
import com.anaadar.akaltakhatsahibpro.activities.news.NewsActivity;
import com.anaadar.akaltakhatsahibpro.constants.Constant;
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

public class SecondPage extends Fragment implements View.OnClickListener {
    LinearLayout btOne, btTwo, btThree, btFour;
    ImageButton mbtOne, mbtTwo, mbtThree, mbtFour;
    boolean play = false;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagetwo, container, false);
        btOne = (LinearLayout) view.findViewById(R.id.daily_news);
        btTwo = (LinearLayout) view.findViewById(R.id.live_kirtan);
        btThree = (LinearLayout) view.findViewById(R.id.live_video);
        btFour = (LinearLayout) view.findViewById(R.id.contactUs);
        mbtOne = (ImageButton) view.findViewById(R.id.mdaily_news);
        mbtTwo = (ImageButton) view.findViewById(R.id.mlive_kirtan);
        mbtThree = (ImageButton) view.findViewById(R.id.mlive_video);
        mbtFour = (ImageButton) view.findViewById(R.id.mcontactUs);
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
            case R.id.daily_news:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case R.id.mdaily_news:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case R.id.contactUs:
                startActivity(new Intent(getActivity(), ContactUs.class));
                break;
            case R.id.mcontactUs:
                startActivity(new Intent(getActivity(), ContactUs.class));
                break;
            case R.id.live_kirtan:
                initExoplayer();
                break;
            case R.id.mlive_kirtan:
                initExoplayer();
                break;
            case R.id.live_video:
                break;
            case R.id.mlive_video:
                break;
        }
    }

    void initExoplayer() {
        if (Constant.haveNetworkConnection(getActivity())) {
            if (play) {
                play = false;
                Constant.player.stop();
            } else {
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
                play = true;
            }
        } else {
            Constant.checkNetworkConnectionWithoutExit(getActivity());
        }
    }
}
