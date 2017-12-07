package com.example.administrator.mytrain.databing;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.bean.DataBingBean;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.databinding.ActivityDataBinding;
import com.example.administrator.mytrain.uitls.ToastUtil;

public class DataActivity extends BaseActivity {

    ActivityDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_data);
        setTitle("数据绑定模块");
        View view= LayoutInflater.from(mContext).inflate(R.layout.activity_data,null);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_data);
        final MulitTypeBean bean=new MulitTypeBean.Builder().name("数据绑定").build();

        binding.setData(bean);
        final DataBingBean dataBingBean=new DataBingBean
                .Builder()
                .name("xingshaoiang")
                .text("很好")
                .build();
        binding.setBean(dataBingBean);
        final DataBingBean dataBingBean1=new DataBingBean
                .Builder()
                .bindEdit("和edittext实现数据双向绑定")
                .build();
        binding.setBean2(dataBingBean1);
        binding.bindEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(dataBingBean1.bindEdit);
            }
        });
        binding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.name="邢少";
            }
        });
        binding.edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                dataBingBean.setName(TextUtils.isEmpty(s.toString())?"":s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataBingBean.setText(TextUtils.isEmpty(s.toString())?"":s.toString());
            }
        });

    }
}
