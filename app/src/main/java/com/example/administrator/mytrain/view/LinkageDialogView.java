package com.example.administrator.mytrain.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.mytrain.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 90589 on 2018/2/9.
 */

public class LinkageDialogView extends Dialog implements View.OnClickListener {
    List<String> data;
    private Context context;
    RecyclerView.Adapter adapter;
    private RecyclerView recycler;
    private View first, second, third;
    private Map<String, List<String>> disData;

    public LinkageDialogView(@NonNull Context context, Map<String, List<String>> data) {
        super(context, R.style.dialog_custom);
        this.context = context;
        this.disData = data;
    }

    public LinkageDialogView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LinkageDialogView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);

        window.setWindowAnimations(R.style.bottom_menu_animation);
        setContentView(R.layout.view_linkage_dialog);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        setCanceledOnTouchOutside(true);
        recycler = ((RecyclerView) findViewById(R.id.recycler));
        first = findViewById(R.id.address_first);
        second = findViewById(R.id.address_second);
        third = findViewById(R.id.address_third);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        loadData();
    }

    private void loadData() {
        data = new ArrayList<>();
        data.addAll(disData.get("first"));
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
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_linkage_dialog_item, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.name.setText(data.get(position));
                viewHolder.name.setGravity(Gravity.LEFT);
                viewHolder.name.setPadding(10,10,10,10);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        recycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        data.clear();
        if (id == R.id.address_first) {
            data.addAll(disData.get("first"));
        } else if (id == R.id.address_second) {
            data.addAll(disData.get("second"));
        } else if (id == R.id.address_third) {
            data.addAll(disData.get("third"));
        }
        adapter.notifyDataSetChanged();
    }
}
