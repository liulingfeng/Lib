package com.llf.lib.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.llf.lib.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by llf on 2016/8/1.
 * 加载的Dialog
 */
public class DialogTools {
    private static Dialog mWaittingDialog;

    public static void showWaittingDialog(Context context) {
        try {
            if (mWaittingDialog != null) {
                mWaittingDialog.dismiss();
                mWaittingDialog = null;
            }

            mWaittingDialog = new Dialog(context, R.style.waitting_dialog);
            mWaittingDialog.setCancelable(false);
            LayoutInflater mInflater = mWaittingDialog.getLayoutInflater();
            View mView = mInflater.inflate(R.layout.layout_waitting_dialog, null);
            mWaittingDialog.show();
            mWaittingDialog.setContentView(mView);
            // 3秒后还未完成任务，则设置为可取消
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (mWaittingDialog != null)
                        mWaittingDialog.setCancelable(true);
                }
            };
            Timer timer = new Timer(true);
            timer.schedule(task, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeWaittingDialog() {
        try {
            if (mWaittingDialog != null)
                mWaittingDialog.dismiss();
            mWaittingDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
