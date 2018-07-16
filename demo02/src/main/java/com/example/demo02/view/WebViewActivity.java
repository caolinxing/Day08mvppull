package com.example.demo02.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.demo02.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //初始化控件
        initView();
        //获取传值
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        //加载
        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new WebChromeClient(){

        });
    }
    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_view);
    }
}
