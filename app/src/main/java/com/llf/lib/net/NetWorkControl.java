package com.llf.lib.net;

import com.llf.lib.eventbus.Userbean;
import com.squareup.okhttp.Request;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;

/**
 * Created by llf on 2016/7/28.
 * 网络处理中心
 */
public class NetWorkControl {
    public static void getUserInfo(String userId){
        OkHttpUtil.getInstanse()._postAsyn("http://www.chunyuyisheng.com/cmsapi/app/update?", userId, true, new OkHttpUtil.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Userbean bean = new Userbean();
                bean.setMessage(e.getMessage());
                EventBus.getDefault().post(bean);   //发送数据
            }

            @Override
            public void onResponse(String response) {
                Userbean userbean = JsonUtil.getObject(response,Userbean.class);
                EventBus.getDefault().post(userbean);
            }
        });
    }
}
