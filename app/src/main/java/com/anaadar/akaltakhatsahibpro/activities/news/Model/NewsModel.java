package com.anaadar.akaltakhatsahibpro.activities.news.Model;

import java.util.ArrayList;

/**
 * Created by Sumit Singh on 3/10/2018.
 */

public class NewsModel {
    public String date;
    public DataModel dataModel;
    public ArrayList<DataModel> baseModels;

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public NewsModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<DataModel> getBaseModels() {
        return baseModels;
    }

    public void setBaseModels(ArrayList<DataModel> baseModels) {
        this.baseModels = baseModels;
    }
}