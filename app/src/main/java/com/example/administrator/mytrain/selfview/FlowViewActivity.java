package com.example.administrator.mytrain.selfview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.view.ProcessImgView;

public class FlowViewActivity extends BaseActivity {
    private ProcessImgView processImg1,processImg2,processImg3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_flow_view);
        String title=getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)){
            setTitle(title);
        }
        initViews();
        setProcessImg1();
        setProcessImg2();
        setProcessImg3();
    }

    private void initViews(){
        processImg1 = (ProcessImgView) findViewById(R.id.p1);
        processImg2 = (ProcessImgView) findViewById(R.id.p2);
        processImg3 = (ProcessImgView) findViewById(R.id.p3);
    }
    private void setProcessImg1(){
        processImg1.setColor(Color.parseColor("#FFFF8C56"));
        processImg1.setProcess(3,2);
        processImg1.setTitle(1,"title1");
        processImg1.setTitle(2,"title2");
        processImg1.setTitle(3,"title3");
        processImg1.setClick(1, new ProcessImgView.Click() {
            @Override
            public void click() {
                Toast.makeText(mContext, "点击第1项", Toast.LENGTH_SHORT).show();
            }
        });
        processImg1.setClick(2, new ProcessImgView.Click() {
            @Override
            public void click() {
                Toast.makeText(mContext, "点击第2项", Toast.LENGTH_SHORT).show();
            }
        });
        processImg1.setClick(3, new ProcessImgView.Click() {
            @Override
            public void click() {
                Toast.makeText(mContext, "点击第3项", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProcessImg2(){
        processImg2.setColor(Color.parseColor("#FFFF8C56"));
        processImg2.setProcess(5,4);
        processImg2.setTitle(1,"title1");
        processImg2.setTitle(2,"title2");
        processImg2.setTitle(3,"title3");
        processImg2.setTitle(4,"title4");
        processImg2.setTitle(5,"title5");
        processImg2.setTitle(6,"title6");
    }

    private void setProcessImg3(){
        processImg3.setColor(Color.parseColor("#809bff"));
        processImg3.setProcess(4,2);
        processImg3.setTitle(1,"标题1");
        processImg3.setTitle(2,"标题2");
        processImg3.setTitle(3,"标题3");
        processImg3.setTitle(4,"标题4");

    }
}
