package com.example.administrator.mytrain.uitls;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by 90589 on 2018/1/30.
 */

public class IntentUtils {

    /**
     * 传入电话号码跳转到，填写短信页面
     * @param phone
     * @param mContext
     */
    public void sendSMS(String phone, Context mContext){
        if (TextUtils.isEmpty(phone))
            return;

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"+phone+""));
        mContext.startActivity(intent);
    }

    /**
     * 跳转到拨打电话界面
     * @param phone
     * @param mContext
     */
    public void phoneBoundary(String phone,Context mContext){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone+""));
        mContext.startActivity(intent);

    }

    /**
     * 直接拨打电话
     * @param phone
     * @param mContext
     */
    public void phoneCallOut(String phone,Context mContext){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phone));
        mContext.startActivity(intent);
    }
}
