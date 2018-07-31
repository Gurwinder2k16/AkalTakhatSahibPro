package com.anaadar.akaltakhatsahibpro.activities.contactUS.questionandsuggestions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.contactUS.GmailModel;

public class Question extends AppCompatActivity implements View.OnClickListener {

    private Button questionAkalTakhat, questionHEAD, questionSGPC;
    GmailModel gmailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionAkalTakhat = (Button) findViewById(R.id.questionAkalTakhat);
        questionHEAD = (Button) findViewById(R.id.questionHEAD);
        questionSGPC = (Button) findViewById(R.id.questionSGPC);
        questionAkalTakhat.setOnClickListener(this);
        questionHEAD.setOnClickListener(this);
        questionSGPC.setOnClickListener(this);
        gmailModel = new GmailModel(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.questionAkalTakhat:
                gmailModel.setEmail("care@akaltakhatsahib.com");
                gmailModel.setSubject("<Question>  <To AkalTakhat Sahib Ji>");
                gmailModel.sendEmail();
                break;
            case R.id.questionHEAD:
                gmailModel.setEmail("info@sgpc.net");
                gmailModel.setSubject("<Question>  <To Jathedar Ji>");
                gmailModel.sendEmail();
                break;
            case R.id.questionSGPC:
                gmailModel.setEmail("info@sgpc.net");
                gmailModel.setSubject("<Question>  <To SGPC Member Committee>");
                gmailModel.sendEmail();
                break;
        }
    }
}
