package com.example.administrator.mytrain.uitls;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mytrain.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by 90589 on 2017/12/3.
 */

public class DialogUtils {

    public static Dialog createProgressDialog(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final AVLoadingIndicatorView mView= ((AVLoadingIndicatorView) view.findViewById(R.id.av));
        mView.setIndicator("BallSpinFadeLoaderIndicator");
        mView.setIndicatorColor(Color.BLUE);
        mView.setBackgroundColor(Color.TRANSPARENT);
        Dialog dialog=new Dialog(context,R.style.MyDialog);
        dialog.setContentView(view);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
             mView.hide();
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                mView.show();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
