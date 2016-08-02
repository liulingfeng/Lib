package com.llf.lib.mvp.login;

import android.text.TextUtils;
import com.llf.lib.mvp.data.ServiceControl;
import com.llf.lib.net.OkHttpUtil;
import com.squareup.okhttp.Request;
import java.io.IOException;

/**
 * Created by llf on 2016/8/1.
 */
public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
    }
    @Override
    public boolean check(String userName,String passWord) {
        if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(passWord)){
            return true;
        }
        if(TextUtils.isEmpty(userName)){
            view.usernameEmpty("请填写用户名");
            return false;
        }

        if(TextUtils.isEmpty(passWord)){
            view.passwordEmpty("请填写密码");
            return false;
        }
        return false;
    }

    @Override
    public void toLogin(String userName,String passWord) {
        if(check(userName,passWord)){
            view.showLoading();
            ServiceControl.cookQuery("http://apis.juhe.cn/cook/query.php", "秘制红烧肉", "273c2928ac2cf691d8f6d3372da48b23", new OkHttpUtil.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    view.hideLoading();
                    view.showResult(response);
                }
            });

        }
    }

    @Override
    public void start() {

    }
}
