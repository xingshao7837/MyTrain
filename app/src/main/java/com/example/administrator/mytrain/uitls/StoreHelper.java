package com.example.administrator.mytrain.uitls;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.mytrain.MyApp;

import java.util.HashMap;
import java.util.Map;

/**
 * 共享文件存储
 * Created by Administrator on 2017/11/18 0018.
 */

public class StoreHelper {
    private SharedPreferences sharedPreferences;
    private static final String FILE_NAME = "share";
    private static final int ACCESSABLE = Context.MODE_PRIVATE;

    public StoreHelper() {
        this(FILE_NAME);
    }

    /**
     * 指定文件存储
     *
     */

    public StoreHelper(String file) {
        this(file,ACCESSABLE);
    }
    /**
     * 指定文件存储
     *
     */

    public static void getStoreHelper(String file){

        MyApp.getApp().getSharedPreferences(file, ACCESSABLE);
    }

    public StoreHelper(String file, int mode) {
        this.sharedPreferences = MyApp.getApp().getSharedPreferences(file, mode);
    }

    public void setString(String key, String value) {
        this.sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return this.sharedPreferences.getString(key, "");
    }

    public void setInteger(String key, int value) {
        this.sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInteger(String key) {
        return this.sharedPreferences.getInt(key, -1);
    }

    public void setFloat(String key, float value) {
        this.sharedPreferences.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return this.sharedPreferences.getFloat(key, 0);
    }

    public void setLong(String key, long value) {
        this.sharedPreferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return this.sharedPreferences.getLong(key, 0);
    }
    public Map<String, ?> getAll() {
        Map<String, ?> map = this.sharedPreferences.getAll();
        return map==null? new HashMap<String, Object>() :map;
    }

    public Map<String, Object> getSpecific(String[] specificField) {
        Map<String, Object> specific = new HashMap<String, Object>();
        Map<String, ?> allStrored = this.sharedPreferences.getAll();
        for (String key : specificField) {
            specific.put(key, allStrored.get(key));
        }
        return specific;
    }

    public void setBoolean(String key, boolean value) {
        this.sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return this.sharedPreferences.getBoolean(key, true);
    }

    public void remove(String key) {
        this.sharedPreferences.edit().remove(key).apply();
    }

    public void setMap(Map<String, ?> map) {
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Integer) {
                this.sharedPreferences.edit().putInt(key, (Integer) value).apply();
            } else if (value instanceof String) {
                this.sharedPreferences.edit().putString(key, (String) value).apply();
            } else if (value instanceof Long) {
                this.sharedPreferences.edit().putLong(key, (Long) value).apply();
            } else if (value instanceof Float) {
                this.sharedPreferences.edit().putFloat(key, (Float) value).apply();
            } else if (value instanceof Boolean) {
                this.sharedPreferences.edit().putBoolean(key, (Boolean) value).apply();
            }
        }
        this.sharedPreferences.edit().apply();
    }

    public Object getValueByKey(String key) {
        if (this.sharedPreferences.contains(key)) {
            Map<String, ?> all = this.sharedPreferences.getAll();
            return all.get(key);
        }
        return null;
    }

    public void flush() {
        this.sharedPreferences.edit().clear().apply();
    }
}
