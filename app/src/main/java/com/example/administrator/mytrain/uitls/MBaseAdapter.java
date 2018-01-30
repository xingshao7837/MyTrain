package com.example.administrator.mytrain.uitls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 90589 on 2018/1/30.
 */

public abstract class MBaseAdapter<T> extends BaseAdapter {

    private List<T> datas;
    private LayoutInflater inflater;
    private Context context;

    public MBaseAdapter(List<T> datas, Context context) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position,convertView,parent);
    }
    public abstract View getItemView(int position, View convertView, ViewGroup parent) ;

    public LayoutInflater getInflater(){return inflater;}

    public void addDatas(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<T> getDatas(){
        return this.datas;
    }

    public void clear(){
        datas.clear();
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        datas.remove(position);
        notifyDataSetChanged();
    }

    public void removeItem(T data){
        datas.remove(data);
        notifyDataSetChanged();
    }
}
