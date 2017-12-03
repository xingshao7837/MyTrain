package com.example.administrator.mytrain.commontitle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;

public class CommonTitleActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_common_title);
        findViewById(R.id.set_title).setOnClickListener(this);
        findViewById(R.id.change_theme).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.set_title){
            setTitle("标题变了，点我").setOnTitleClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"你好啊",Toast.LENGTH_SHORT).show();
                }
            });
        }else if (id==R.id.change_theme){
            changeTheme(0xff303F9F);
        }
    }
}
