package com.anaadar.akaltakhatsahibpro.activities.mainMenu.side_menu_page;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.About.Activities.AboutAkalTakhatSahib;
import com.anaadar.akaltakhatsahibpro.activities.About.Activities.AboutSGPC;
import com.anaadar.akaltakhatsahibpro.activities.mainMenu.Adapter.CustomPagerAdapter;
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

import info.hoang8f.android.segmented.SegmentedGroup;

public class MenuDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab, fabLeft, fabRight;
    SegmentedGroup segmented;
    int index = 0;
    TextView textView;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //showCustomDialog();
        if (!haveNetworkConnection()) {
            checkNetworkConnection();
        }
        viewPager = (ViewPager) findViewById(R.id.viewpagerMenu);
        textView = (TextView) findViewById(R.id.comments);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/amrlipi_.ttf");
        textView.setTypeface(custom_font);
        segmented = (SegmentedGroup) findViewById(R.id.segmented_group);
        segmented.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                viewPager.setCurrentItem((segmented.indexOfChild((RadioButton) group.findViewById(group.getCheckedRadioButtonId()))) + 2);
            }
        });
        try {
            fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility(View.GONE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.player.stop();
                    fab.setVisibility(View.GONE);
                }
            });
            fabLeft = (FloatingActionButton) findViewById(R.id.left);
            fabLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            });

            fabRight = (FloatingActionButton) findViewById(R.id.right);
            fabRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            });
        } catch (Exception e) {

        }


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      /*  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);*/
        ImageButton button = (ImageButton) findViewById(R.id.toogle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        //  toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position >= 2 && position <= 4) {
                    ((RadioButton) segmented.getChildAt(position - 2)).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(1);

        startComments();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection!!");
        builder.setMessage("Please turn on internet connection to continue services!");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.japji_sahib) {
            initExoplayer("http://old.sgpc.net/CDN/Japji_Sahib.mp3");
        } else if (id == R.id.jaap_sahib) {
            initExoplayer("http://old.sgpc.net/CDN/Jaap_Sahib.mp3");
        } else if (id == R.id.savaiye) {
            initExoplayer("http://old.sgpc.net/CDN/Savaiye.mp3");
        } else if (id == R.id.Chaupai_Sahib) {
            initExoplayer("http://old.sgpc.net/CDN/Chaupai_Sahib.mp3");
        } else if (id == R.id.anaadshaib) {
            initExoplayer("http://old.sgpc.net/CDN/Anand_Sahib.mp3");
        } else if (id == R.id.rehrass) {
            initExoplayer(" http://sgpc.net/nitnem2/Rehraas%20sahib.mp3\n");
        } else if (id == R.id.ardass) {
            initExoplayer("http://sgpc.net/nitnem2/Ardaas.mp3");
        } else if (id == R.id.nav_about_akaltakhat) {
            startActivity(new Intent(this, AboutAkalTakhatSahib.class));
        } else if (id == R.id.nav_about_sgpc) {
            startActivity(new Intent(this, AboutSGPC.class));
        } else if (id == R.id.nav_share) {
            shareApp();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initExoplayer(String streamUrl) {
        if (Constant.haveNetworkConnection(this)) {
            if (Constant.player != null) {
                Constant.player.stop();
                fab.setVisibility(View.GONE);
            }
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            TrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"), defaultBandwidthMeter);
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(streamUrl), dataSourceFactory, extractorsFactory, null, null);
            Constant.player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Constant.player.prepare(mediaSource);
            Constant.player.setPlayWhenReady(true);
            fab.setVisibility(View.VISIBLE);
        } else {
            Constant.checkNetworkConnectionWithoutExit(this);
        }
    }

    protected void showAbout() {
        View messageView = getLayoutInflater().inflate(R.layout.about, null, false);
        TextView textView = (TextView) messageView.findViewById(R.id.about_credits);
        int defaultColor = textView.getTextColors().getDefaultColor();
        textView.setTextColor(defaultColor);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.gurugranthji);
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }

    final Handler handler = new Handler();
    private Runnable updateTask;

    private void startComments() {
        final int length = getResources().getStringArray(R.array.slogan_punjabi).length - 1;
        updateTask = new Runnable() {
            @Override
            public void run() {
                if (index > length) {
                    index = 0;
                }
                textView.setText(getResources().getStringArray(R.array.slogan_punjabi)[index]);
                index++;
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(updateTask, 5000);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

   /* private void initInstruction(View view) {
        TextView instruction = (TextView) view.findViewById(R.id.instruction);
        TextView instructionNote = (TextView) view.findViewById(R.id.instructionnote);
        TextView sgpcMessage = (TextView) view.findViewById(R.id.sgpc_messagetv);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/amrlipi_.ttf");
        sgpcMessage.setTypeface(custom_font);
        instruction.setTypeface(custom_font);
        instructionNote.setTypeface(custom_font);
        instruction.setText(getResources().getString(R.string.instruction));
        instructionNote.setText(getResources().getString(R.string.instructionnote));
    }*/

    private void showCustomDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogpageinstr, null);
        //initInstruction(alertLayout);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("GuruBani Darshan Instruction's");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download Gurbani Darshan official application by Sri Akal Takhat Sahib Link : https://play.google.com/store/apps/details?id=com.anaadar.akaltakhatsahibpro&hl=en_IN";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Gurbani Darshan Android App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(null);
        }
        if (handler != null && updateTask != null) {
            handler.removeCallbacks(updateTask);
        }
        super.onDestroy();
    }
}
