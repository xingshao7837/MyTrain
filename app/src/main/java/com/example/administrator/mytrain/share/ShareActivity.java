package com.example.administrator.mytrain.share;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.MyApp;
import com.example.administrator.mytrain.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        findViewById(R.id.file).setOnClickListener(this);
        findViewById(R.id.image).setOnClickListener(this);
        findViewById(R.id.text).setOnClickListener(this);
        findViewById(R.id.many).setOnClickListener(this);
        findViewById(R.id.self_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        File dir = new File(MyApp.getApp().getExternalCacheDir() + "");
        if (id == R.id.file) {
            File file = new File(dir, "哈哈哈哈哈.pdf");
            myShare(file);
        } else if (id == R.id.image) {
            File file = new File(dir, "image.jpg");
            myShare(file);
        } else if (id == R.id.text) {
            myShare(null);
        }else if (id==R.id.many){
            ArrayList<Uri> list=new ArrayList<>();
            File file = new File(dir, "哈哈哈哈哈.pdf");
            list.add(Uri.fromFile(file));
            file = new File(dir, "image.jpg");
            list.add(Uri.fromFile(file));
            Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
            share.setType("*/*");
            share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, list);
            startActivity(Intent.createChooser(share, "分享一下"));
        }else if (id==R.id.self_view){
            // TODO: 2017/11/17 0017 待实现自定义分享界面 
            getShareApps();
        }
    }

    public void getShareApps() {
//        List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        PackageManager pManager = mContext.getPackageManager();
        Object str = pManager.queryIntentActivities(intent,
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        Log.i("-----",str.toString());
    }


    private void myShare(File file) {
        if (file!=null){
            //以下代码为我在应用中使用的
            Intent share = new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_STREAM,
                    Uri.fromFile(file));
            share.setType("*/*");//此处可发送多种文件
            startActivity(Intent.createChooser(share, "Share"));
        }else {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            String title = "标题";
            String extraText="给大家介绍一个好网站，www.jcodecraeer.com";
            share.putExtra(Intent.EXTRA_TEXT, extraText);
            if (title != null) {
                share.putExtra(Intent.EXTRA_SUBJECT, title);
            }
            startActivity(Intent.createChooser(share, "分享一下"));
        }

    }
}
