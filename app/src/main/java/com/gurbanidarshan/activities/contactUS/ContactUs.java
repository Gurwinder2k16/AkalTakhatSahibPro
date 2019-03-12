package com.gurbanidarshan.activities.contactUS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gurbanidarshan.R;
import com.gurbanidarshan.activities.contactUS.questionandsuggestions.Question;
import com.gurbanidarshan.activities.contactUS.questionandsuggestions.SuggestionsView;
import com.gurbanidarshan.activities.contactUS.requests.Requests;


public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Button QuestionToAkalTakhat = (Button) findViewById(R.id.Questions);
        QuestionToAkalTakhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, Question.class));
            }
        });
        Button RequestToSGPC = (Button) findViewById(R.id.Requests);
        RequestToSGPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, Requests.class));
            }
        });
        Button GiveSuggestion = (Button) findViewById(R.id.Suggestions);
        GiveSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, SuggestionsView.class));
            }
        });

    }
}
