package com.example.administrator.mytrain.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mytrain.R;

/**
 * Created by 90589 on 2017/12/7.
 */

public class VerifyCodeView extends RelativeLayout {
    private EditText editText;
    private TextView[] textViews;
    private View[] views;
    private View[] cursor;
    private static int MAX = 6;
    private String inputContent;

    public VerifyCodeView(Context context) {
        this(context, null);
    }

    public VerifyCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_verify_code, this);

        textViews = new TextView[MAX];
        views = new View[MAX];
        cursor = new View[MAX];
        textViews[0] = (TextView) findViewById(R.id.tv_0);
        textViews[1] = (TextView) findViewById(R.id.tv_1);
        textViews[2] = (TextView) findViewById(R.id.tv_2);
        textViews[3] = (TextView) findViewById(R.id.tv_3);
        textViews[4] = (TextView) findViewById(R.id.tv_4);
        textViews[5] = (TextView) findViewById(R.id.tv_5);
        views[0] = findViewById(R.id.view1);
        views[1] = findViewById(R.id.view2);
        views[2] = findViewById(R.id.view3);
        views[3] = findViewById(R.id.view4);
        views[4] = findViewById(R.id.view5);
        views[5] = findViewById(R.id.view6);
        cursor[0] = findViewById(R.id.cursor1);
        cursor[1] = findViewById(R.id.cursor2);
        cursor[2] = findViewById(R.id.cursor3);
        cursor[3] = findViewById(R.id.cursor4);
        cursor[4] = findViewById(R.id.cursor5);
        cursor[5] = findViewById(R.id.cursor6);
        editText = (EditText) findViewById(R.id.edit_text_view);

        editText.setCursorVisible(false);//隐藏光标
        setEditTextListener();
    }

    private void setEditTextListener() {
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (TextUtils.isEmpty(inputContent)||inputContent.length()<1){
                        cursor[0].setVisibility(VISIBLE);
                    }else {
                        if (inputContent.length()<MAX)
                            cursor[inputContent.length()].setVisibility(VISIBLE);
                    }

                }else {
                    for (View view : cursor) {
                        view.setVisibility(GONE);
                    }
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = editText.getText().toString();

                if (inputCompleteListener != null) {
                    if (inputContent.length() >= MAX) {
                        inputCompleteListener.inputComplete();
                    } else {
                        inputCompleteListener.invalidContent();
                    }
                }

                for (int i = 0; i < MAX; i++) {
                    cursor[i].setVisibility(GONE);
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                        views[i].setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else {
                        textViews[i].setText("");
                        views[i].setBackgroundColor(getResources().getColor(R.color.gray));
                    }
                }
                if (inputContent.length()<MAX)
                    cursor[inputContent.length()].setVisibility(VISIBLE);
            }
        });
    }


    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {

        void inputComplete();

        void invalidContent();
    }

    public String getEditContent() {
        return inputContent;
    }

}
