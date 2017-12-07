package com.example.administrator.mytrain.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.administrator.mytrain.BR;


/**
 * Created by 90589 on 2017/12/6.
 */

public class DataBingBean extends BaseObservable {


    @Bindable
    public String name;

    @Bindable
    public String bindEdit;

    public void setBindEdit(String bindEdit) {
        this.bindEdit = bindEdit;
        notifyPropertyChanged(BR.bindEdit);
    }

    public String text;

    private DataBingBean(Builder builder) {
        setName(builder.name);
        bindEdit = builder.bindEdit;
        setText(builder.text);
    }


    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setText(String text) {
        this.text = text;
    }

    public static final class Builder {
        private String name;
        private String bindEdit;
        private String text;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder bindEdit(String val) {
            bindEdit = val;
            return this;
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public DataBingBean build() {
            return new DataBingBean(this);
        }
    }
}
