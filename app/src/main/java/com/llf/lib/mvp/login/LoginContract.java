package com.llf.lib.mvp.login;

import com.llf.lib.mvp.BasePresenter;
import com.llf.lib.mvp.BaseView;

/**
 * Created by llf on 2016/8/1.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void usernameEmpty(String empty);
        void passwordEmpty(String empty);
    }

    interface Presenter extends BasePresenter{
        boolean check(String userName,String passWord);
        void toLogin(String userName,String passWord);
    }
}
