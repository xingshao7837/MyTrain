package com.example.administrator.mytrain.share;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.MyApp;
import com.example.administrator.mytrain.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class ShareActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_share);
        setTitle("分享模块");
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
//            getShareApps();

            Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
            weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
            weibo.setPlatformActionListener(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                }

                @Override
                public void onError(Platform platform, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(Platform platform, int i) {

                }
            }); // 设置分享事件回调

            weibo.authorize();//单独授权
            weibo.showUser(null);//授权并获取用户信息
            showShare(null);
        }
    }

    private void showShare(String platform) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        //启动分享
        oks.show(this);
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
