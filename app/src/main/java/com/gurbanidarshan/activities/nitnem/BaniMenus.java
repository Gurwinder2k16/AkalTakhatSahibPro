package com.gurbanidarshan.activities.nitnem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.nitnem.constants.Constants;
import com.gurbanidarshan.activities.nitnem.pageAdapter.BaniMenuListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class BaniMenus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_bani_menus);
        getSupportActionBar().setTitle("Nitnem Bani's List");
        ListView mlistView = (ListView) findViewById(R.id.list);
        //---------------
        ArrayList<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.bani_menu)));
        BaniMenuListAdapter baniMenuListAdapter = new BaniMenuListAdapter(this, R.layout.banimenucustomlayout, list);
        mlistView.setAdapter(baniMenuListAdapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    case 0:
                        Constants.file = "japjiSahib";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                    case 1:
                        Constants.file = "jaapSahib";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                    case 2:
                        Constants.file = "anandSahib";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                    case 3:
                        Constants.file = "tavPrasadSvayeae";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                    case 4:
                        Constants.file = "kabyo_bach_baynti";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                    case 5:
                        Constants.file = "rehraasSahib";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                    case 6:
                        Constants.file = "sukhmaniSahib";
                        startActivity(new Intent(BaniMenus.this, NitnemBani.class));
                        break;
                }
            }

        });
    }
}
