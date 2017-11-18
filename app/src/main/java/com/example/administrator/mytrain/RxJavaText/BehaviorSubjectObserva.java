package com.example.administrator.mytrain.RxJavaText;

import android.text.TextUtils;

import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.uitls.StoreHelper;
import com.google.gson.Gson;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class BehaviorSubjectObserva {
    public BehaviorSubjectObserva() {
    }
    private static Subject<MulitTypeBean> disMenusSubject;
    private static StoreHelper storeHelper = new StoreHelper(BehaviorSubjectObserva.class.getName());
    private static MulitTypeBean disMenus;

    public static Subject<MulitTypeBean> getDudaoLSubject() {
        if(disMenusSubject==null){
            if (getBean()!= null) {
                disMenusSubject = BehaviorSubject.createDefault(getBean()).toSerialized();
            } else {
                disMenusSubject = BehaviorSubject.<MulitTypeBean>create().toSerialized();
            }
        }
        return disMenusSubject;
    }

    public static MulitTypeBean getBean() {
        String dl = storeHelper.getString("behavior");
        if (TextUtils.isEmpty(dl)) return null;
        Gson gson=new Gson();
        BehaviorSubjectObserva.disMenus = gson.fromJson(dl, MulitTypeBean.class);
        return disMenus;
    }

    public static void setBean(MulitTypeBean disMenus) {
        Gson gson=new Gson();
        storeHelper.setString("behavior", gson.toJson(disMenus));
        BehaviorSubjectObserva.disMenus = disMenus;
        if( BehaviorSubjectObserva.disMenus!=null) {
            getDudaoLSubject().onNext( BehaviorSubjectObserva.disMenus);
        }
    }
}
