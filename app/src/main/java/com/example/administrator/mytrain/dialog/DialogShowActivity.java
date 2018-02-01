package com.example.administrator.mytrain.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.wang.avi.AVLoadingIndicatorView;

public class DialogShowActivity extends BaseActivity implements View.OnClickListener{

    TextView dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_dialog_show);
        dialog = ((TextView) findViewById(R.id.dialog));
        dialog.setOnClickListener(this);
        findViewById(R.id.type).setOnClickListener(this);
        setTitle("自定义对话框");
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.dialog){
            if ("显示进度对话框".equals(dialog.getText().toString().trim())){
                dialog.setText("隐藏进度对话框");
                showProgressDialog();
            }else {
                dialog.setText("显示进度对话框");
                hideProgressDialog();
            }
        }else if (id==R.id.type){
            startActivity(new Intent(mContext,ProgressDialogTypeActivity.class));
        }
    }
}
