package com.example.administrator.mytrain.selfview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends BaseActivity {

    private RecyclerView recyc;
    RecyclerView.Adapter adapter;
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_view);
        setTitle("自定义view模块");
        recyc = ((RecyclerView) findViewById(R.id.recycler));
        data=new ArrayList<>();

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
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_view_item, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.name.setText(data.get(position));
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent();
                        switch (position){
                            case 0:
                                intent.setClass(mContext,FlowViewActivity.class);
                                intent.putExtra("title",data.get(position));
                                break;
                            case 1:
                                intent.setClass(mContext,EditCodeActivity.class);
                                intent.putExtra("title",data.get(position));
                                break;
                        }
                        startActivity(intent);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        recyc.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyc.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        data.add("流程进度view");
        data.add("自定义验证码框");
        adapter.notifyDataSetChanged();
    }
}
