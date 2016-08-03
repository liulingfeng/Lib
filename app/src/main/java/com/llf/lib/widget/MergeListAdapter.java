package com.llf.lib.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.llf.lib.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LLF on 2016/8/3.
 */
public class MergeListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataHolder> mDataList = new ArrayList<>();

    public MergeListAdapter(Context context, List<DataHolder> dataList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
        }
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = mInflater.inflate(R.layout.item_delete, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataHolder item = mDataList.get(position);

        holder.title.setText(item.title);
        item.rootView = (LinearLayout)convertView.findViewById(R.id.lin_root);
        item.rootView.scrollTo(0,0);
        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
    }

    public static class DataHolder {
        public String title;
        public LinearLayout rootView;
    }
}
