package com.llf.lib.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.llf.lib.R;

/**
 * Created by llf on 2016/7/25.
 * http://blog.csdn.net/huachao1001/article/details/51853662
 */
public class ToolbarActivity extends AppCompatActivity{
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        initView();
    }

    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("首页");
        mToolbar.setSubtitle("我是副标题");
        mToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);
    }
}
