package com.example.administrator.mytrain;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 默认context
     */
    public BaseActivity mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity",getClass().getName());
    }
}
