package com.example.administrator.mytrain.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.location.LocationActivity;
import com.example.administrator.mytrain.uitls.ToastUtil;

public class MyReceiver extends BroadcastReceiver {
    NotificationManager manager;
    @Override
    public void onReceive(Context context, Intent intent) {

        manager= ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        ToastUtil.show("收到广播了");
        String name=intent.getStringExtra("name");
        Intent goIntent=new Intent(context, LocationActivity.class);
        goIntent.putExtra("name",name);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,goIntent, 0);

        Notification notification=new NotificationCompat.Builder(context)
                .setContentTitle("我的通知")
                .setContentText("小主，十万火急的事啊！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.info_apha)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        manager.notify(1,notification);
    }
}
