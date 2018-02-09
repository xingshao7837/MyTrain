package com.example.administrator.mytrain.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytrain.R;

/**
 * Created by 90589 on 2018/2/8.
 */

public class OpenAccountFragmentOne extends BaseFragment implements View.OnClickListener{

    private TextView next;
    private ImageView image1,image2;
    private Runnable runnable;

    public void setNextClick(Runnable runnable){
        this.runnable=runnable;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_open_account_one,null);
        next = ((TextView) view.findViewById(R.id.next));
        image1 = ((ImageView) view.findViewById(R.id.image1));
        image2 = ((ImageView) view.findViewById(R.id.image2));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.next){
            if (runnable!=null)
                runnable.run();
        }
    }
}
