package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.uitls.ToastUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.functions.Consumer;

public class RxjavaActivity extends BaseActivity implements View.OnClickListener{

    MulitTypeBean mulitTypeBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.look).setOnClickListener(this);

        BehaviorSubjectObserva.getDudaoLSubject().subscribe(new Consumer<MulitTypeBean>() {
            @Override
            public void accept(MulitTypeBean mulitTypeBean) throws Exception {
                ToastUtil.show("涨工资了money"+mulitTypeBean.money);
            }
        });
        mulitTypeBean=new MulitTypeBean.Builder()
                .name("内部类")
                .days(23)
                .money(567)
                .time(new Date())
                .list(Arrays.asList(new String[]{"list1","list2","list3"}))
                .map(new HashMap<String, String>(){{put("name","姓　　名");put("value","我的");}
                })
                .build();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.look){
         mulitTypeBean.money+=100;
         BehaviorSubjectObserva.setBean(mulitTypeBean);
        }
    }
}
