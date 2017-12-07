package com.example.administrator.mytrain.location;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.uitls.LocationUtils;

import java.util.Timer;
import java.util.TimerTask;

public class LocationActivity extends BaseActivity implements View.OnClickListener {

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_location);
        findViewById(R.id.start).setOnClickListener(this);
        display = ((TextView) findViewById(R.id.display));
        setTitle("开启定位");
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.start){
            Timer timer=new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    Log.i("---","location");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            LocationUtils.getCNBylocation(mContext);
//                        }
//                    });
//                }
//            },5000);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.i("---","location");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LocationUtils.getCNBylocation(mContext);
                        }
                    });
                }
            },2000,1000);

        }
    }
}
