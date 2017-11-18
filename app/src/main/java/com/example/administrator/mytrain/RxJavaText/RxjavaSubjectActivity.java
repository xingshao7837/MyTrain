package com.example.administrator.mytrain.RxJavaText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.bean.MulitTypeBean;
import com.example.administrator.mytrain.uitls.ToastUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class RxjavaSubjectActivity extends BaseActivity implements View.OnClickListener{

    MulitTypeBean mulitTypeBean;
    Subject<String> asyncSubject;
    Subject<String> publishSubject;
    Subject<String> replaySubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_subject);
        findViewById(R.id.BehaviorSubject).setOnClickListener(this);
        findViewById(R.id.AsyncSubject).setOnClickListener(this);
        findViewById(R.id.PublishSubject).setOnClickListener(this);
        findViewById(R.id.ReplaySubject).setOnClickListener(this);


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

        asyncSubject = AsyncSubject.<String>create().toSerialized();
        asyncSubject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("--asyncSubject",s);
            }
        });
        publishSubject = PublishSubject.<String>create().toSerialized();
        replaySubject = ReplaySubject.<String>create().toSerialized();

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.BehaviorSubject){
            mulitTypeBean.money+=100;
            BehaviorSubjectObserva.setBean(mulitTypeBean);
        }else if (id==R.id.AsyncSubject){
            asyncSubject.onNext("asyncSubject1");
            asyncSubject.onNext("asyncSubject2");
            asyncSubject.onNext("asyncSubject3");
            asyncSubject.onComplete();
            asyncSubject.onNext("asyncSubject4");
            asyncSubject.onNext("asyncSubject5");

        }else if (id==R.id.PublishSubject){
            publishSubject.onNext("publishSubject1");
            publishSubject.onNext("publishSubject2");
            publishSubject.subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    Log.i("--publishSubject",s);
                }
            });
            publishSubject.onNext("publishSubject3");
            publishSubject.onNext("publishSubject4");
        }else if (id==R.id.ReplaySubject){
            replaySubject.onNext("replaySubject:pre1");
            replaySubject.onNext("replaySubject:pre2");
            replaySubject.onNext("replaySubject:pre3");
            replaySubject.subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    Log.i("--replaySubject",s);
                }
            });
            replaySubject.onNext("replaySubject:after1");
            replaySubject.onNext("replaySubject:after2");
        }
    }
}
