package com.example.administrator.mytrain.databing;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.databinding.ActivityDataBinding;

public class DataActivity extends BaseActivity {

    ActivityDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        View view= LayoutInflater.from(mContext).inflate(R.layout.activity_data,null);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_data);
        final MulitTypeBean bean=new MulitTypeBean.Builder().name("数据绑定").build();

        binding.setData(bean);
        binding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.name="邢少";
            }
        });

    }
}
