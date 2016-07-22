package com.llf.lib.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.llf.lib.R;
import java.util.ArrayList;

/**
 * Created by llf on 2016/7/21.
 * 没有经过封装的Adapter
 */
public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder>{
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> datas;

    public GeneralAdapter(Context context, ArrayList<String> datas){
        mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mLayoutInflater.inflate(R.layout.item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
}
