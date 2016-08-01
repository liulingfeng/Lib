package com.llf.lib.mvp.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.llf.lib.R;
import com.llf.lib.util.DialogTools;
import com.llf.lib.util.ToastUtil;

/**
 * Created by llf on 2016/8/1.
 * 登录
 */
public class LoginActivity extends Activity implements LoginContract.View{
    private LoginContract.Presenter mPresenter;
    private EditText userNameEt,passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEt = (EditText)findViewById(R.id.userName);
        passwordEt = (EditText)findViewById(R.id.password);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void login(View v){
        mPresenter.toLogin(userNameEt.getText().toString(),passwordEt.getText().toString());
    }

    @Override
    public void usernameEmpty(String empty) {
        ToastUtil.show(empty);
    }

    @Override
    public void passwordEmpty(String empty) {
        ToastUtil.show(empty);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        //这个方法只在View是Fragment的情况下需要
    }

    @Override
    public void showLoading() {
        DialogTools.showWaittingDialog(this);
    }

    @Override
    public void hideLoading() {
        DialogTools.closeWaittingDialog();
    }

    @Override
    public void showResult(String data) {
        ToastUtil.show(data);
    }
}
