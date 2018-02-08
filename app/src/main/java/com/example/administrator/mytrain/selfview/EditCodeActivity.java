package com.example.administrator.mytrain.selfview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.view.VerifyCodeView;

public class EditCodeActivity extends BaseActivity {

    VerifyCodeView verifyCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_edit_code);
        setTitle("自定义验证码框");
        verifyCodeView = (VerifyCodeView) findViewById(R.id.verify_code_view);
        verifyCodeView.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                Toast.makeText(mContext, "inputComplete: " + verifyCodeView.getEditContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void invalidContent() {

            }
        });
    }
}
