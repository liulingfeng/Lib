package com.llf.lib.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llf on 2016/7/21.
 * 封装的Adapter
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{
    private int itemId;
    private Context mContext;
    private List<T> datas;
    private OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context,int itemId){
        this.itemId = itemId;
        this.mContext = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<T> datas){
        datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = new BaseViewHolder(LayoutInflater.from(mContext).inflate(itemId,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mOnItemClickListener!=null){
//                    mOnItemClickListener.onClick(position);
//                }
//            }
//        });
        convert(holder,datas.get(position));
    }
    public abstract void convert(BaseViewHolder viewHolder,T item);

    public interface OnItemClickListener{
        void onClick(int positon);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
}
