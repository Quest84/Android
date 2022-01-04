package com.quest.questhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView miWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miWebView = findViewById(R.id.webViewName);
        miWebView.getSettings().setJavaScriptEnabled(true);
        miWebView.setWebViewClient(new WebViewClient());
        miWebView.loadUrl("http://192.168.0.7");

    }
}
