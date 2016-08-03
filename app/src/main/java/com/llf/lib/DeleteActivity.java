package com.llf.lib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.llf.lib.util.ToastUtil;
import com.llf.lib.widget.MergeListAdapter;
import com.llf.lib.widget.SpecailListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class DeleteActivity extends Activity implements MergeListAdapter.DeleteLisener {
    private SpecailListView mSpecailListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mSpecailListView = (SpecailListView) findViewById(R.id.listview);
        final List<MergeListAdapter.DataHolder> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MergeListAdapter.DataHolder item = new MergeListAdapter.DataHolder();
            item.title = "第" + i + "项";
            items.add(item);
        }
        MergeListAdapter adapter = new MergeListAdapter(this, items);
        adapter.setDeleteListener(this);
        mSpecailListView.setAdapter(adapter);

        mSpecailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show("你点击了"+position);
            }
        });
    }

    @Override
    public void onItemDeleteClick(int position) {
        ToastUtil.show(position + "被删除了");
    }
}
