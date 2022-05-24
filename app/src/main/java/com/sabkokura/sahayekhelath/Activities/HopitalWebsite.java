package com.sabkokura.sahayekhelath.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sabkokura.sahayekhelath.Fragments.FragmentHospital;
import com.sabkokura.sahayekhelath.R;

public class HopitalWebsite extends AppCompatActivity {
    WebView webView;
    TextView hosName, progStat;
    ImageView back, forward, refresh, close;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.hospitalWebsite);
        hosName = (TextView) findViewById(R.id.title);
        back = (ImageView) findViewById(R.id.backInWebsite);
        forward = (ImageView) findViewById(R.id.forwardInWebsite);
        refresh = (ImageView) findViewById(R.id.refreshInWebsite);
        close = (ImageView) findViewById(R.id.exitFromWebsite);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progStat = (TextView) findViewById(R.id.progressText);

        Intent intent = getIntent();

        String URL = intent.getStringExtra("link");
        String Name = intent.getStringExtra("name");
        hosName.setText(Name);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        webView.setWebViewClient(new MyBrowser());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goForward();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
                progressBar.setVisibility(View.VISIBLE);
                progStat.setVisibility(View.VISIBLE);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FragmentHospital.class));
            }
        });


    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            progStat.setVisibility(View.GONE);
        }
    }
}