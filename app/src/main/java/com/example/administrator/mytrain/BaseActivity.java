package com.example.administrator.mytrain;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.mytrain.uitls.DialogUtils;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity",getClass().getName());
        baseActivityProgressDialog= DialogUtils.createProgressDialog(mContext);
        progressDialogShow=new AtomicInteger();
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
