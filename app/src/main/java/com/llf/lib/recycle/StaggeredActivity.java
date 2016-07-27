package com.llf.lib.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import com.llf.lib.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llf on 2016/7/27.
 * 利用RecycleView实现瀑布流
 */
public class StaggeredActivity extends Activity{
    private RecyclerView mRecyclerView;
    private List<Integer> datas = new ArrayList<>();
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        datas.add(R.mipmap.ic_girls_1);
        datas.add(R.mipmap.ic_girls_2);
        datas.add(R.mipmap.ic_girls_3);
        datas.add(R.mipmap.ic_girls_4);
        datas.add(R.mipmap.ic_girls_5);
        datas.add(R.mipmap.ic_girls_6);
        datas.add(R.mipmap.ic_girls_7);
        datas.add(R.mipmap.ic_girls_8);
        datas.add(R.mipmap.ic_girls_9);
        datas.add(R.mipmap.ic_girls_10);
        mBaseAdapter = new BaseAdapter<Integer>(this,R.layout.item_staggered) {
            @Override
            public void convert(BaseViewHolder viewHolder, Integer item) {
                ImageView iv = viewHolder.getView(R.id.masonry_item_img);
                iv.setImageResource(item);
            }
        };
        mRecyclerView.setAdapter(mBaseAdapter);
        mBaseAdapter.setDatas(datas);
    }
}
