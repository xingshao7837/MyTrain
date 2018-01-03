package com.example.administrator.mytrain.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.uitls.ToastUtil;

public class BroadcastMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_broadcast_main);
        setTitle("广播").setOnTitleClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.administrator.mytrain.broadcast");
                intent.putExtra("name","xingshao");
                sendBroadcast(intent);

//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                Notification notification = new NotificationCompat.Builder(mContext)
//                        .setContentTitle("This is content title")
//                        .setContentText("This is content test")
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                        .build();
//                manager.notify(1, notification);
            }
        });

    }
}
