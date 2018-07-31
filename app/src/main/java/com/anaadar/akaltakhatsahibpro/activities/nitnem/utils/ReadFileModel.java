package com.anaadar.akaltakhatsahibpro.activities.nitnem.utils;

import android.content.Context;
import android.util.Log;

import com.anaadar.akaltakhatsahibpro.activities.nitnem.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Sumit Singh on 3/5/2018.
 */

public class ReadFileModel {
    Context context;
    public ReadFileModel(Context context) {
        this.context = context;
        Constants.data.clear();
        Constants.jump = 0;
    }

    public ArrayList<String> readFile() {
        ArrayList<String> temp = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("gutka_data/" + Constants.file + ".txt")));
            temp = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                temp.add(line);
                Log.d("data", line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Constants.data.addAll(temp);
        Log.d("Linr", String.valueOf(temp.size()));
        Constants.page = (temp.size() / 5) + 1;
        Log.d("Page", String.valueOf(Constants.page));
        generatepara();
        return temp;
    }

    void generatepara() {
        Constants.paragraph.clear();
        for (int i = 0; i < Constants.page; i++) {
            Constants.paragraph.add((viewText()));
            Constants.jump = Constants.jump + 5;
        }
    }

    String viewText() {
        String temp = null;
        try {
            temp = Constants.data.get(Constants.jump) + "\n";
            temp += Constants.data.get(Constants.jump + 1) + "\n";
            temp += Constants.data.get(Constants.jump + 2) + "\n";
            temp += Constants.data.get(Constants.jump + 3) + "\n";
            temp += Constants.data.get(Constants.jump + 4) + "\n";
            return temp;
        } catch (Exception e) {
            return temp;
        }
    }
}
