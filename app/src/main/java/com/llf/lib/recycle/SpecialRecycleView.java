package com.llf.lib.recycle;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by llf on 2016/7/27.
 * 将addHeadView和removeHeadView的方法赋予RecycleView，这样就和ListView一样的用法了
 */
public class SpecialRecycleView extends RecyclerView{
    public SpecialRecycleView(Context context) {
        this(context,null);
    }

    public SpecialRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpecialRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeadView(int headViewRes){
        if(getAdapter() instanceof BaseAdapter){
            BaseAdapter adapter = (BaseAdapter) getAdapter();
            adapter.setHeadView(headViewRes);
        }
    }

    public void remoteHeadView(){
        if(getAdapter() instanceof BaseAdapter){
            BaseAdapter adapter = (BaseAdapter) getAdapter();
            adapter.removeHeadView();
        }
    }
}
