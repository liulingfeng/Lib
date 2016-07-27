package com.llf.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.llf.lib.recycle.BaseAdapter;
import com.llf.lib.recycle.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BaseAdapter mBaseAdapter;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        for(int i=0;i<25;i++){
            datas.add("帅帅的");
        }

        mBaseAdapter = new BaseAdapter<String>(this,R.layout.item) {
            @Override
            public void convert(BaseViewHolder viewHolder, String item) {
                TextView tv = viewHolder.getView(R.id.tv);
                tv.setText(item);
            }
        };

        mRecyclerView.setAdapter(mBaseAdapter);
        mBaseAdapter.setDatas(datas);
        mBaseAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int positon) {
                Toast.makeText(MainActivity.this, positon + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
