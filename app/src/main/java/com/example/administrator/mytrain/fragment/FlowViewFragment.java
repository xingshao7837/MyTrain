package com.example.administrator.mytrain.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytrain.R;

import org.w3c.dom.Text;

/**
 * Created by 90589 on 2018/2/8.
 */

public class FlowViewFragment extends BaseFragment implements View.OnClickListener{

    public String num;
    private TextView text,next;
    private Runnable runnable;

    public void setNextClick(Runnable runnable){
        this.runnable=runnable;
    }

    public void setNextClick(String num,Runnable runnable){
        this.num=num;
        this.runnable=runnable;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_flowview,null);
        text = ((TextView) view.findViewById(R.id.fragment_flowview_text));
        next = ((TextView) view.findViewById(R.id.fragment_flowview_next));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(num)){
            text.setText(text.getText()+"< "+num+" >");
        }
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.fragment_flowview_next){
            if (runnable!=null)
                runnable.run();
        }
    }
}
