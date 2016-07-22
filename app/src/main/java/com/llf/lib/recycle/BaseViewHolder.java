package com.llf.lib.recycle;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by llf on 2016/7/21.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> viewArray;

    public BaseViewHolder(View itemView) {
        super(itemView);

        viewArray = new SparseArray<>();
    }

    /**
     * 获取布局中的View
     * @param viewId view的Id
     * @param <T> View的类型
     * @return view
     */
    protected <T extends View>T getView(@IdRes int viewId){
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewArray.put(viewId, view);
        }
        return (T) view;
    }
}
