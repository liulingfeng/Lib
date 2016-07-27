package com.llf.lib;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.llf.lib.recycle.BaseAdapter;
import com.llf.lib.recycle.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView深入浅出
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRecyclerView;
    private BaseAdapter mBaseAdapter;
    private List<String> datas = new ArrayList<>();
    //下拉刷新
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //添加头部
    private LinearLayout headView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.headview_demo,null);
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
        mBaseAdapter.setHeadView(headView);
        mBaseAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int positon) {
                Toast.makeText(MainActivity.this, positon + "", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int totalItemCount = layoutManager.getItemCount();
                        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                        if(lastVisibleItem == totalItemCount-1){
                            Toast.makeText(MainActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
