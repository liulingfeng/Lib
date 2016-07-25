package com.llf.lib.design;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by llf on 2016/7/25.
 */
public class MoveTextView extends TextView{
    private int lastX;
    private int lastY;

    public MoveTextView(Context context) {
        this(context,null);
    }

    public MoveTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MoveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();//相对于屏幕的位置
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if(lastX!=0) {
                    CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                    int left = layoutParams.leftMargin + x - lastX;
                    int top = layoutParams.topMargin + y - lastY;
                    layoutParams.leftMargin = left;
                    layoutParams.topMargin = top;
                    setLayoutParams(layoutParams);
                    requestLayout();
                }
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
