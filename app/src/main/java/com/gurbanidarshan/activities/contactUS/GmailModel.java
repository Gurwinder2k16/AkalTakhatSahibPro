package com.gurbanidarshan.activities.contactUS;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Sumit Singh on 3/3/2018.
 */

public class GmailModel {
    String email, subject, body;
    Context context;

    public GmailModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + getEmail()));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getSubject());
        //emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        //emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
        getContext().startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
    }
}
