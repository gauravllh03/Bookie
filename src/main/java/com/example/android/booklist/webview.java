package com.example.android.booklist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.os.Handler;

import com.example.android.booklist.R;

public class webview extends AppCompatActivity {
    ProgressBar progress;


    public static final String LOG_TAG = MainActivity.class.getName();
    WebView webView;
    String isbn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        /*Bundle extras = getIntent().getExtras();
        String d="";
        WebView mWebView;

        if (extras != null) {
            d= extras.getString("down");
            Log.e(LOG_TAG,d);
            // and get whatever type user account id is
        }
        mWebView = (WebView) findViewById(R.id.web);

        mWebView.getSettings().setBuiltInZoomControls(true);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(d);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(true);
        mWebView.requestFocus();*/
        final RelativeLayout r=(RelativeLayout)findViewById(R.id.loadingPanel);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                r.setVisibility(View.GONE); //Do something after 100ms
            }
        }, 8000);

        Intent i = getIntent();
        isbn = i.getStringExtra("down");
        boolean text=i.getBooleanExtra("text",false);
        boolean img=i.getBooleanExtra("img",false);
        String mlink=i.getStringExtra("view");
        String full=i.getStringExtra("full");
        Log.e(LOG_TAG,isbn);
        if(text==true || img==true) {
            webView = (WebView) findViewById(R.id.web);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setHorizontalScrollBarEnabled(true);
            String html = "<!DOCTYPE html '-//W3C//DTD XHTML 1.0 Strict//EN'\n" +
                    "  'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>\n" +
                    "<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
                    "  <head>\n" +
                    "    <meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>\n" +
                    "    <meta http-equiv='content-type' content='text/html; charset=utf-8'/>\n" +
                    "    <title></title>\n" +
                    "    <script type='text/javascript' src='https://www.google.com/books/jsapi.js'></script>\n" +
                    "    <script type='text/javascript'>\n" +
                    "      google.books.load();\n" +
                    "\n" +
                    "      var viewer;\n" +
                    "      function nextStep() {\n" +
                    "        this.viewer.nextPage();\n" +
                    "      }\n" +
                    "\n" +
                    "      function backStep() {\n" +
                    "        this.viewer.previousPage();\n" +
                    "      }\n" +
                    "      \n" +
                    "      function initialize() {\n" +
                    "        viewer = new google.books.DefaultViewer(document.getElementById('viewerCanvas'));\n" +
                    "        viewer.load('ISBN:" + isbn + "');\n" +
                    "      }\n" +
                    "\n" +
                    "      google.books.setOnLoadCallback(initialize);\n" +
                    "    </script>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div id='viewerCanvas' style='width: 400px; height: 700px'></div>\n" +
                    "  </body>\n" +
                    "</html>";

            webView.loadData(html, "text/html", "utf-8");
        }
        else
        {
            webView = (WebView) findViewById(R.id.web);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setHorizontalScrollBarEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(full);


        }

    }
}