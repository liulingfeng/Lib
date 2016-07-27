package com.llf.lib.recycle;

import android.support.v7.widget.RecyclerView;

/**
 * Created by llf on 2016/7/27.
 */
public class MoveInfo {
    public RecyclerView.ViewHolder holder;
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public MoveInfo(RecyclerView.ViewHolder holder,
                    int fromX, int fromY, int toX, int toY) {
        this.holder = holder;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }
}
