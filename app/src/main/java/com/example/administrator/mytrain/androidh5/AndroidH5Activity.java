package com.example.administrator.mytrain.androidh5;

import android.content.Intent;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;

public class AndroidH5Activity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_h5);
        findViewById(R.id.loadurl).setOnClickListener(this);
        findViewById(R.id.h5android).setOnClickListener(this);
        findViewById(R.id.androidh5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.loadurl){
            startActivity(new Intent(mContext,LookWebActivity.class)
            .putExtra("url","http://news.baidu.com"));
        }else if (id==R.id.h5android){

        }else if (id==R.id.androidh5){

        }
    }
}
