package com.example.administrator.mytrain.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.view.BottomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90589 on 2018/2/9.
 */

public class OpenAccountFragmentTwo extends BaseFragment implements View.OnClickListener{
    private TextView next,office,city;
    private Runnable runnable;
    BottomListView bottomListView;

    public void setNextClick(Runnable runnable){
        this.runnable=runnable;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_open_account_two,null);
        next = ((TextView) view.findViewById(R.id.next));
        office = ((TextView) view.findViewById(R.id.office));
        city = ((TextView) view.findViewById(R.id.city));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        next.setOnClickListener(this);
        office.setOnClickListener(this);
        city.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.next){
            if (runnable!=null)
                runnable.run();
        }else if (id==R.id.office){
            List<String> data=new ArrayList<>();
            data.add("办事人员");
            data.add("商业人员");
            data.add("服务人员");
            data.add("军人");
            data.add("私营业主");
            data.add("其他");
            if (bottomListView==null){
                bottomListView=new BottomListView(getActivity(),data);
            }

            if (!bottomListView.isShowing())
                bottomListView.show();
        }
    }
}
