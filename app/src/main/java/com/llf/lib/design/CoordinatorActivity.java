package com.llf.lib.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.llf.lib.R;

/**
 * Created by llf on 2016/7/25.
 * 是一个FrameLayout
 */
public class CoordinatorActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
    }
}
