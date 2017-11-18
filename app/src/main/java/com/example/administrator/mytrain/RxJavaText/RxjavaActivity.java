package com.example.administrator.mytrain.RxJavaText;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.uitls.ToastUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.functions.Consumer;

public class RxjavaActivity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.look).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.look){
         startActivity(new Intent(mContext,RxjavaSubjectActivity.class));
        }
    }
}
