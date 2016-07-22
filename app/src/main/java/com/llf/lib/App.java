package com.llf.lib;

import android.app.Application;
import com.llf.lib.imageloaderreal.UniversalImageLoaderStractery;

/**
 * Created by llf on 2016/7/21.
 */
public class App extends Application{
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        UniversalImageLoaderStractery.init(this);
    }
}
