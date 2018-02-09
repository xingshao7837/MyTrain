package com.example.administrator.mytrain.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.uitls.ToastUtil;
import com.example.administrator.mytrain.view.BottomListView;
import com.example.administrator.mytrain.view.LinkageDialogView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 90589 on 2018/2/9.
 */

public class OpenAccountFragmentTwo extends BaseFragment implements View.OnClickListener{
    private TextView next,office,city;
    private Runnable runnable;
    BottomListView bottomListView;
    LinkageDialogView linkageDialog;

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
                bottomListView.setBottomListSelect(new BottomListView.BottomListSelect() {
                    @Override
                    public void getListSelect(int position, String selectStr) {
                        ToastUtil.show(selectStr);
                        office.setText(selectStr);
                    }
                });
            }

            if (!bottomListView.isShowing())
                bottomListView.show();
        }else if (id==R.id.city){
            if (linkageDialog==null){
                Map<String,List<String>> map=new HashMap<>();
                List<String> data=new ArrayList<>();
                data.add("省一");
                data.add("省二");
                data.add("省三");
                data.add("省四");
                data.add("省五");
                data.add("省六");
                map.put("first",data);
                data=new ArrayList<>();
                data.add("市区一");
                data.add("市区二");
                data.add("市区三");
                data.add("市区四");
                data.add("市区五");
                data.add("市区六");
                map.put("second",data);
                data=new ArrayList<>();
                data.add("区一");
                data.add("区二");
                data.add("区三");
                data.add("区四");
                data.add("区五");
                data.add("区六");
                map.put("third",data);

                linkageDialog=new LinkageDialogView(getActivity(),map);
            }
            if (!linkageDialog.isShowing())
                linkageDialog.show();
        }
    }
}
