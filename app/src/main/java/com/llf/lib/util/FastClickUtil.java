package com.llf.lib.util;

/**
 * Created by llf on 2016/5/10.
 * 防止快速点击，点击多次
 */
public class FastClickUtil {
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
