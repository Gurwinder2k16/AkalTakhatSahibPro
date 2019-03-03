package com.anaadar.akaltakhatsahibpro.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.SplashScreen;
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
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> pMessageList = remoteMessage.getData();
        if (pMessageList.containsKey("PlayRadio")) {
            initExoplayer();
        } else if (pMessageList.containsKey("HukamnamaSahib")) {
            showMessage(
                    "101",
                    "akaltakhatsahibapp",
                    getResources().getString(R.string.app_name),
                    pMessageList.get("HukamnamaSahib"),
                    true
            );
        } else {
            showMessage(
                    "101",
                    "akaltakhatsahibapp",
                    getResources().getString(R.string.app_name),
                    pMessageList.get("Message"),
                    false
            );
        }
    }

    void showMessage(String pChanneld, String pChannelName, String pTitle, String pMessage, boolean showHukamNama) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    pChanneld,
                    pChannelName,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder notification = new NotificationCompat.Builder(
                    this,
                    pChanneld)
                    .setContentTitle(pTitle)
                    .setContentText(pMessage)
                    .setSmallIcon(R.drawable.sgpclogo);
            Intent notificationIntent = new Intent(this, SplashScreen.class);
            if (showHukamNama) {
                notificationIntent.putExtra(Constant.hukamnama, showHukamNama);
            }
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(contentIntent);
            notificationManager.notify(1, notification.build());
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.sgpclogo)
                    .setContentTitle(pTitle)
                    .setContentText(pMessage);
            Intent notificationIntent = new Intent(this, SplashScreen.class);
            if (showHukamNama) {
                notificationIntent.putExtra(Constant.hukamnama, showHukamNama);
            }
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }

    private void initExoplayer() {
        if (Constant.haveNetworkConnection(getBaseContext())) {
            final String streamUrl = "http://sgpc.net:8070/live32"; //bbc world service url
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            TrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getBaseContext(), Util.getUserAgent(getBaseContext(), "mediaPlayerSample"), defaultBandwidthMeter);
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(streamUrl), dataSourceFactory, extractorsFactory, null, null);
            Constant.player = ExoPlayerFactory.newSimpleInstance(getBaseContext(), trackSelector);
            Constant.player.prepare(mediaSource);
            Constant.player.setPlayWhenReady(true);
            Constant.RadioIsplay = true;
        }
    }
}