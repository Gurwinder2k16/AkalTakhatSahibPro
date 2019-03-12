package com.gurbanidarshan.activities.guru_sahiban;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.guru_sahiban.pageAdapter.GuruSahibanListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class GuruSahibanMenus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_bani_menus);
        getSupportActionBar().setTitle("Guru Sahiban's List");
        ListView mlistView = (ListView) findViewById(R.id.list);
        //---------------
        ArrayList<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.GuruSahiban_Menu)));
        GuruSahibanListAdapter baniMenuListAdapter = new GuruSahibanListAdapter(this, R.layout.banimenucustomlayout, list);
        mlistView.setAdapter(baniMenuListAdapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    case 0:
                        startIntentWithIndex(0);
                        break;
                    case 1:
                        startIntentWithIndex(1);
                        break;
                    case 2:
                        startIntentWithIndex(2);
                        break;
                    case 3:
                        startIntentWithIndex(3);
                        break;
                    case 4:
                        startIntentWithIndex(4);
                        break;
                    case 5:
                        startIntentWithIndex(5);
                        break;
                    case 6:
                        startIntentWithIndex(6);
                        break;
                    case 7:
                        startIntentWithIndex(7);
                        break;
                    case 8:
                        startIntentWithIndex(8);
                        break;
                    case 9:
                        startIntentWithIndex(9);
                        break;
                }
            }

        });
    }

    void startIntentWithIndex(int Index) {
        Intent intent = new Intent(GuruSahibanMenus.this, GuruSahibanHistory.class);
        intent.putExtra("GuruSahibMessage", Index);
        startActivity(intent);
    }
}
