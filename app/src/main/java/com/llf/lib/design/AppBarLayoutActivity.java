package com.llf.lib.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.llf.lib.R;

/**
 * Created by llf on 2016/7/25.
 */
public class AppBarLayoutActivity extends AppCompatActivity{
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbarlayout);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("美眉");
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
    }
}
