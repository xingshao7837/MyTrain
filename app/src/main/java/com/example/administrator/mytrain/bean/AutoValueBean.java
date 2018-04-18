package com.example.administrator.mytrain.bean;

import com.google.auto.value.AutoValue;

/**
 *
 * Created by 90589 on 2018/4/18.
 */

@AutoValue
public abstract class AutoValueBean {
    abstract String name();
    abstract String addr();
    abstract int age();
    abstract String gender();
    abstract String hobby();
    abstract String sign();
}
