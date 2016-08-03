package com.llf.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.llf.lib.util.DensityUtil;

/**
 * Created by llf on 2016/8/3.
 * 可以滑动删除的ListView
 */
public class SpecailListView extends ListView{
    private Context mContext;
    private LinearLayout itemRoot;
    private int mlastX = 0;

    public SpecailListView(Context context) {
        this(context,null);
    }

    public SpecailListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpecailListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int maxLength = DensityUtil.dip2px(mContext, 96);

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //我们想知道当前点击了哪一行
                int position = pointToPosition(x, y);
                if (position != INVALID_POSITION) {
                    MergeListAdapter.DataHolder data = (MergeListAdapter.DataHolder) getItemAtPosition(position);
                    itemRoot = data.rootView;
                }
            }
            case MotionEvent.ACTION_MOVE: {
                int scrollX = itemRoot.getScrollX();
                int newScrollX = scrollX + mlastX - x;
                if (newScrollX < 0) {
                    newScrollX = 0;
                } else if (newScrollX > maxLength) {
                    newScrollX = maxLength;
                }
                itemRoot.scrollTo(newScrollX, 0);
            }
            break;
            case MotionEvent.ACTION_UP: {
                int scrollX = itemRoot.getScrollX();
                int newScrollX = scrollX + mlastX - x;
                if (scrollX > maxLength / 2) {
                    newScrollX = maxLength;
                } else {
                    newScrollX = 0;
                }
                itemRoot.scrollTo(newScrollX, 0);
            }
            break;
        }

        mlastX = x;
        return super.onTouchEvent(event);
    }
}
