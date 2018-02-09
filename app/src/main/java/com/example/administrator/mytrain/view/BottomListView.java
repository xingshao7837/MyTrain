package com.example.administrator.mytrain.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.selfview.EditCodeActivity;
import com.example.administrator.mytrain.selfview.FlowViewActivity;
import com.example.administrator.mytrain.selfview.OpenAccountFlowActivity;

import java.util.List;


/**
 * Created by 90589 on 2018/2/9.
 */

public class BottomListView extends Dialog{

    List<String> data;
    private Context context;
    private RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    BottomListSelect bottomListSelect;

    public interface BottomListSelect{
        void getListSelect(int position,String selectStr);
    }

    public void setBottomListSelect(BottomListSelect select){
        this.bottomListSelect=select;
    }

    public BottomListView(@NonNull Context context,List<String> data) {
        super(context, R.style.dialog_custom);
        this.context=context;
        this.data=data;
    }

    public BottomListView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomListView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);

        window.setWindowAnimations(R.style.bottom_menu_animation);
        setContentView(R.layout.view_bottom_list);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

//        //设置宽度
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width = display.getWidth() * 9 / 10;
//        getWindow().setAttributes(params);

        setCanceledOnTouchOutside(true);
        recyclerView = ((RecyclerView) findViewById(R.id.bottom_list_recycler));

        if (data!=null&&data.size()>0)
            loadData();
    }

    private void loadData() {
        adapter = new RecyclerView.Adapter() {
            class ViewHolder extends RecyclerView.ViewHolder {
                TextView name;
                private ViewHolder(View itemView) {
                    super(itemView);
                    name = ((TextView) itemView.findViewById(R.id.recycler_item_text));

                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_view_item, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.name.setText(data.get(position));
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   if (bottomListSelect!=null){
                       dismiss();
                       bottomListSelect.getListSelect(position,data.get(position));
                   }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


}
