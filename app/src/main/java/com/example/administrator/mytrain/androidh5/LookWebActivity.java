package com.example.administrator.mytrain.androidh5;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.mytrain.BaseActivity;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class LookWebActivity extends BaseActivity{

    WebView webView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(mContext);
        webView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(webView);
        WebSettings ws = webView.getSettings();
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);

        WebViewClient mWebviewclient = new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }
            /**
             * 防止加载网页时调起系统浏览器
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("http://") || url.contains("https://")) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                //监听网页是否加载完成
            }
        };
        webView.setWebViewClient(mWebviewclient);

        String str =
                getIntent().getStringExtra("url");
        webView.loadUrl(str);
    }
}
