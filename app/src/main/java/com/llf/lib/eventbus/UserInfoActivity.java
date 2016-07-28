package com.llf.lib.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.llf.lib.R;
import com.llf.lib.net.NetWorkControl;
import com.llf.lib.util.APPLog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by llf on 2016/7/28.
 * 个人中心
 */
public class UserInfoActivity extends Activity{
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        EventBus.getDefault().register(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkControl.getUserInfo("111245");
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(Userbean userbean){
        APPLog.d(userbean.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
