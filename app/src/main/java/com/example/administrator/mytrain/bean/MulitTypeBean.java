package com.example.administrator.mytrain.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class MulitTypeBean {
    @SerializedName("names")
    public String name;

    public int days;
    public long money;
    public Date time;
    public ObjectToJson json;
    public List<String> list;
    public Map<String,String> map;

    private MulitTypeBean(Builder builder) {
        name = builder.name;
        days = builder.days;
        money = builder.money;
        time = builder.time;
        json = builder.json;
        list = builder.list;
        map = builder.map;
    }

    @Override
    public String toString() {
        return name+"/"+days+"/"+money+"/"
                +time.getTime()+json.toString()
                +"/list.size"+list.size()
                +"/map.size"+map.keySet().size();
    }

    public static final class Builder {
        private String name;
        private int days;
        private long money;
        private Date time;
        private ObjectToJson json;
        private List<String> list;
        private Map<String, String> map;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder days(int val) {
            days = val;
            return this;
        }

        public Builder money(long val) {
            money = val;
            return this;
        }

        public Builder time(Date val) {
            time = val;
            return this;
        }

        public Builder json(ObjectToJson val) {
            json = val;
            return this;
        }

        public Builder list(List<String> val) {
            list = val;
            return this;
        }

        public Builder map(Map<String, String> val) {
            map = val;
            return this;
        }

        public MulitTypeBean build() {
            return new MulitTypeBean(this);
        }
    }
}
