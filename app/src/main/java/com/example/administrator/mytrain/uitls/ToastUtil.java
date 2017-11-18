package com.example.administrator.mytrain.uitls;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.mytrain.MyApp;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class ToastUtil {
    private ToastUtil() {
    }
    public static void show(final String info) {
        if (!TextUtils.isEmpty(info))
            Observable.just(info).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    Toast.makeText(MyApp.getApp(), s, Toast.LENGTH_SHORT).show();
                }
            });

    }

    public static void show(final int info) {
        Observable.just(info).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) {
                Toast.makeText(MyApp.getApp(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
