package com.anaadar.akaltakhatsahibpro.services;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationData implements Serializable {
    /*
    * {   "description":"Tap to See today Sri Guru Granth Sahib Ji Hukamnama",   "playKirtan":false,   "seeHukamnama":true, }*/

    @SerializedName("description")
    String pDesc="Tap to see notification";
    String pTitile = "Gurbani Darshan";
    @SerializedName("playKirtan")
    Boolean pPlayRadio=false;
    @SerializedName("seeHukamnama")
    Boolean pHukamnamaShib=false;
    @SerializedName("message")
    String pMessage="";

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpTitile() {
        return pTitile;
    }

    public void setpTitile(String pTitile) {
        this.pTitile = pTitile;
    }

    public Boolean getpPlayRadio() {
        return pPlayRadio;
    }

    public void setpPlayRadio(Boolean pPlayRadio) {
        this.pPlayRadio = pPlayRadio;
    }

    public Boolean getpHukamnamaShib() {
        return pHukamnamaShib;
    }

    public void setpHukamnamaShib(Boolean pHukamnamaShib) {
        this.pHukamnamaShib = pHukamnamaShib;
    }

    public String getpMessage() {
        return pMessage;
    }

    public void setpMessage(String pMessage) {
        this.pMessage = pMessage;
    }
}
