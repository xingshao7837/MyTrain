package com.example.administrator.mytrain;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.mytrain.uitls.DialogUtils;
import com.example.administrator.mytrain.uitls.StatusBarCompat;

import java.security.PublicKey;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 默认context
     */
    public BaseActivity mContext = this;
    private Dialog baseActivityProgressDialog;
    private AtomicInteger progressDialogShow;
    Toolbar toolbar;
    private FrameLayout body;
    private FrameLayout toolbar_layout;
    private FrameLayout rootView;
    View.OnClickListener onTitleClickListener;
    View.OnClickListener onLeftClickListener;
    public TextView toolbar_title;
    private View rootColor;
    private TextView toolbarLeft;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity",getClass().getName());
        baseActivityProgressDialog= DialogUtils.createProgressDialog(mContext);
        progressDialogShow=new AtomicInteger();
        setContentView(R.layout.activity_base);
        rootColor = findViewById(R.id.root_color);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarCompat.compat(this);
        body = (FrameLayout) findViewById(R.id.body);
        toolbar_layout = (FrameLayout) findViewById(R.id.toolbar_layout);
        rootView = body;
        toolbarLeft = ((TextView) findViewById(R.id.toolbar_left));
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleClickListener!=null)
                    onTitleClickListener.onClick(v);
            }
        });
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftClickListener!=null){
                    onLeftClickListener.onClick(v);
                }else {
                    finish();
                }
            }
        });

    }
    /**
     * 改变头布局
     *
     * @param view 布局
     * @return this
     */
    public BaseActivity setToolbar(View view) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar_layout.removeAllViews();
        toolbar_layout.addView(view, params);
        toolbar_layout.setVisibility(View.VISIBLE);
        return this;
    }

    public BaseActivity changeTheme(int color){
        rootColor.setBackgroundColor(color);
//        StatusBarCompat.compat(this,color);
        return this;
    }

    /**
     * 设置布局
     *
     * @param id 布局id
     * @return this
     */
    public BaseActivity setView(int id) {
        body.removeAllViews();
        View bodyView = LayoutInflater.from(mContext).inflate(id, rootView, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        body.addView(bodyView, params);
        return this;
    }

    /**
     * 设置标题的点击事件
     *
     * @param listener 点击事件
     * @return this
     */
    public BaseActivity setOnTitleClick(View.OnClickListener listener) {
        this.onTitleClickListener = listener;
        return this;
    }

    public BaseActivity setTitle(String title){
        toolbar_title.setText(title);
        return this;
    }

    public void showProgressDialog(){
        if (this.progressDialogShow.getAndIncrement() <= 0){
            this.progressDialogShow.set(1);
            baseActivityProgressDialog.show();
        }
    }

    public void hideProgressDialog(){
        if (this.progressDialogShow.decrementAndGet() <= 0){
            baseActivityProgressDialog.hide();
        }

    }

    public void dismissAllProgressDialog() {
        this.baseActivityProgressDialog.dismiss();
        this.progressDialogShow.set(0);
    }


}
