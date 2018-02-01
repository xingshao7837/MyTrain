package com.example.administrator.mytrain.location;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.uitls.LocationUtils;
import com.example.administrator.mytrain.uitls.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

public class LocationActivity extends BaseActivity implements View.OnClickListener {

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_location);
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:18342910644"));
                startActivity(intent);

            }
        });
        display = ((TextView) findViewById(R.id.display));
        setTitle("开启定位");
        String info=getIntent().getStringExtra("name");
        ToastUtil.show(info);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.start){
//            Timer timer=new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    Log.i("---","location");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            LocationUtils.getCNBylocation(mContext, new LocationUtils.LocationInfoListening() {
//                                @Override
//                                public void setLocationInfoListening(Location location) {
//
//                                }
//                            });
//                        }
//                    });
//                }
//            },2000,1000);
            LocationUtils.getCNBylocation(mContext, new LocationUtils.LocationInfoListening() {
                @Override
                public void setLocationInfoListening(Location location) {
                    Log.i("---","Latitude="+location.getLatitude()+"Longitude="+location.getLongitude());
                    Uri uri = Uri.parse("geo:"+location.getLatitude()+","+location.getLongitude()+"");
                    Intent it = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);
                }
            });

        }
    }
}
