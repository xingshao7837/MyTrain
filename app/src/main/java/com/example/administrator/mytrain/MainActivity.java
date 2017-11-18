package com.example.administrator.mytrain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.mytrain.RxJavaText.RxjavaActivity;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.bean.ObjectToJson;
import com.example.administrator.mytrain.share.ShareActivity;
import com.google.gson.Gson;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(this);
        findViewById(R.id.andincrement).setOnClickListener(this);
        findViewById(R.id.object_to_json).setOnClickListener(this);
        findViewById(R.id.rxjava).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.text){
            startActivity(new Intent(mContext, ShareActivity.class));
        }else if (id==R.id.andincrement){
            AtomicInteger integer=new AtomicInteger();
            Log.i("--get",integer.get()+"");
            Log.i("--getAndIncrement",integer.getAndIncrement()+"");
            Log.i("--getAndIncrement结果",integer.get()+"");
            integer.set(1);
            Log.i("--set",integer.get()+"");
            Log.i("--getAndDecrement",integer.getAndDecrement()+"");
            Log.i("--getAndDecrement结果",integer.get()+"");
        }else if (id==R.id.object_to_json){
            ObjectToJson objectToJson=new ObjectToJson
                    .Builder()
                    .name("邢少强")
                    .age("24")
                    .address("浦东新区")
                    .location("北艾路").build();
            Gson gson=new Gson();
            String json=gson.toJson(objectToJson);
            Log.i("--json",json);
            objectToJson=gson.fromJson(json,ObjectToJson.class);
            Log.i("--bean",objectToJson.toString());

            MulitTypeBean mulitTypeBean=new MulitTypeBean.Builder()
                    .name("内部类")
                    .days(23)
                    .json(objectToJson)
                    .money(567)
                    .time(new Date())
                    .list(Arrays.asList(new String[]{"list1","list2","list3"}))
                    .map(new HashMap<String, String>(){{put("name","姓　　名");put("value","我的");}
                    })
                    .build();
            String json2=gson.toJson(mulitTypeBean);
            Log.i("--含有内部类转换为json",json2);
            mulitTypeBean=null;

            mulitTypeBean=gson.fromJson(json2,MulitTypeBean.class);
            mulitTypeBean.money=899;
            Log.i("--json to object",mulitTypeBean.toString());
        }else if (id==R.id.rxjava){
            startActivity(new Intent(mContext, RxjavaActivity.class));
        }

    }
}
