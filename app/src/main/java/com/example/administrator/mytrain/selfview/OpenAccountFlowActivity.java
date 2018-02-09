package com.example.administrator.mytrain.selfview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.FrameLayout;


import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.fragment.BaseFragment;
import com.example.administrator.mytrain.fragment.OpenAccountFragmentOne;
import com.example.administrator.mytrain.uitls.FragmentUtil;
import com.example.administrator.mytrain.view.ProcessImgView;

import java.util.ArrayList;
import java.util.List;

public class OpenAccountFlowActivity extends BaseActivity {

    private ProcessImgView flowView;
    List<BaseFragment> listFragments;
    private FragmentManager fragmentManager;
    FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_open_account_flow);
        String title=getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)){
            setTitle(title);
        }
        initView();
        initProcess();
        initFragment();
    }




    private void initView() {
        flowView = ((ProcessImgView) findViewById(R.id.flow_view));
        framelayout = ((FrameLayout) findViewById(R.id.containerId));
    }
    private void initProcess() {
        flowView.setColor(Color.parseColor("#FFFF8C56"));
        flowView.setProcess(4,1);
        flowView.setTitle(1,"身份验证");
        flowView.setTitle(2,"基本信息");
        flowView.setTitle(3,"绑定银行卡");
        flowView.setTitle(4,"密码设置");
    }
    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        listFragments=new ArrayList<>();
        OpenAccountFragmentOne open1=new OpenAccountFragmentOne();
        open1.setNextClick(new Runnable() {
            @Override
            public void run() {
                flowView.setProcess(4,2);
                FragmentUtil.showFragment(fragmentManager, listFragments, listFragments.get(1), R.id.containerId);
            }
        });
        OpenAccountFragmentOne open2=new OpenAccountFragmentOne();
        open2.setNextClick(new Runnable() {
            @Override
            public void run() {
                flowView.setProcess(4,3);
                FragmentUtil.showFragment(fragmentManager, listFragments, listFragments.get(2), R.id.containerId);
            }
        });
        OpenAccountFragmentOne open3=new OpenAccountFragmentOne();
        open3.setNextClick(new Runnable() {
            @Override
            public void run() {
                flowView.setProcess(4,4);
                FragmentUtil.showFragment(fragmentManager, listFragments, listFragments.get(3), R.id.containerId);
            }
        });
        OpenAccountFragmentOne open4=new OpenAccountFragmentOne();
        open4.setNextClick(new Runnable() {
            @Override
            public void run() {

            }
        });
        listFragments.add(open1);
        listFragments.add(open2);
        listFragments.add(open3);
        listFragments.add(open4);
        FragmentUtil.showFragment(fragmentManager, listFragments, listFragments.get(0), R.id.containerId);
    }
}
