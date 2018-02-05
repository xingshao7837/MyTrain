package com.example.administrator.mytrain.location;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.uitls.GDLocationUtils;
import com.example.administrator.mytrain.uitls.LocationUtils;
import com.example.administrator.mytrain.uitls.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

public class LocationActivity extends BaseActivity implements View.OnClickListener {

    private TextView display,displayXT;
    private AMapLocationClient locationClient = null;
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
        findViewById(R.id.gd_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationClient!=null)
              locationClient.startLocation();
            }
        });
        display = ((TextView) findViewById(R.id.display));
        displayXT = ((TextView) findViewById(R.id.display_xt));
        setTitle("开启定位");
        String info=getIntent().getStringExtra("name");
        ToastUtil.show(info);
        locationClient= GDLocationUtils.getInstance(mContext).getLocationClient(false, new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                display.setText("高德坐标：Latitude:"+aMapLocation.getLatitude()+",Longitude:"+
                        aMapLocation.getLongitude());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.start){
            LocationUtils.getCNBylocation(mContext, new LocationUtils.LocationInfoListening() {
                @Override
                public void setLocationInfoListening(Location location) {
                    Log.i("---","Latitude="+location.getLatitude()+"Longitude="+location.getLongitude());
                    displayXT.setText("系统坐标：Latitude:"+location.getLatitude()+",Longitude:"+
                            location.getLongitude());
                    Uri uri = Uri.parse("geo:"+location.getLatitude()+","+location.getLongitude()+"");
                    Intent it = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(it);
                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
        }
    }
}
