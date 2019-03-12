package com.gurbanidarshan.activities.contactUS.requests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.contactUS.GmailModel;

public class Requests extends AppCompatActivity implements View.OnClickListener {
    private Button RequestAkalTakhat, RequestHEAD, RequestSGPC;
    GmailModel gmailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        RequestAkalTakhat = (Button) findViewById(R.id.requestAkalTakhat);
        RequestHEAD = (Button) findViewById(R.id.requestHEAD);
        RequestSGPC = (Button) findViewById(R.id.requestSGPC);
        RequestAkalTakhat.setOnClickListener(this);
        RequestHEAD.setOnClickListener(this);
        RequestSGPC.setOnClickListener(this);
        gmailModel = new GmailModel(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.requestAkalTakhat:
                gmailModel.setEmail("care@akaltakhatsahib.com");
                gmailModel.setSubject("<Request>  <To AkalTakhat Sahib Ji>");
                gmailModel.sendEmail();
                break;
            case R.id.requestHEAD:
                gmailModel.setEmail("info@sgpc.net");
                gmailModel.setSubject("<Request>  <To Jathedar Ji>");
                gmailModel.sendEmail();
                break;
            case R.id.requestSGPC:
                gmailModel.setEmail("info@sgpc.net");
                gmailModel.setSubject("<Request>  <To SGPC Member Committee>");
                gmailModel.sendEmail();
                break;
        }
    }
}