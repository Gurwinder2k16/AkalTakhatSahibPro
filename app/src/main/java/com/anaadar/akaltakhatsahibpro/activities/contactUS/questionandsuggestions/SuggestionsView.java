package com.anaadar.akaltakhatsahibpro.activities.contactUS.questionandsuggestions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.contactUS.GmailModel;

public class SuggestionsView extends AppCompatActivity implements View.OnClickListener {
    private Button SuggestionAkalTakhat, SuggestionHEAD, SuggestionSGPC;
    GmailModel gmailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);
        SuggestionAkalTakhat = (Button) findViewById(R.id.suggestionAkalTakhat);
        SuggestionHEAD = (Button) findViewById(R.id.suggestionHEAD);
        SuggestionSGPC = (Button) findViewById(R.id.suggestionSGPC);
        SuggestionAkalTakhat.setOnClickListener(this);
        SuggestionHEAD.setOnClickListener(this);
        SuggestionSGPC.setOnClickListener(this);
        gmailModel = new GmailModel(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.suggestionAkalTakhat:
                gmailModel.setEmail("care@akaltakhatsahib.com");
                gmailModel.setSubject("<Suggestion>  <To AkalTakhat Sahib Ji>");
                gmailModel.sendEmail();
                break;
            case R.id.suggestionHEAD:
                gmailModel.setEmail("info@sgpc.net");
                gmailModel.setSubject("<Suggestion>  <To Jathedar Ji>");
                gmailModel.sendEmail();
                break;
            case R.id.suggestionSGPC:
                gmailModel.setEmail("info@sgpc.net");
                gmailModel.setSubject("<Suggestion>  <To SGPC Member Committee>");
                gmailModel.sendEmail();
                break;
        }
    }
}
