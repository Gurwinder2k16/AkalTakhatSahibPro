package com.gurbanidarshan.Webview;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gurbanidarshan.R;

public class Contact_web extends Activity {
    public String str;
    Intent n;
    ProgressBar progressBar;
    TextView tv;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        n = getIntent();
        str = n.getStringExtra("Message");
        if (str.isEmpty()) {
            str = "http://www.sgpc.net";
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_web);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        tv = (TextView) findViewById(R.id.textView1);
        webView = (WebView) findViewById(R.id.webview01);
        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(str);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        }
    }

}