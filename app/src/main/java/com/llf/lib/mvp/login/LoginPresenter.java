package com.llf.lib.mvp.login;

import android.text.TextUtils;

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
            view.showResult("登录成功");
        }
    }

    @Override
    public void start() {

    }
}
