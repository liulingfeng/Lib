package com.llf.lib.mvp.data;

import com.llf.lib.net.OkHttpUtil;
import com.squareup.okhttp.FormEncodingBuilder;

/**
 * Created by llf on 2016/8/2.
 * 接口中心
 */
public class ServiceControl {
    public static void cookQuery(String url, String menu, String key, OkHttpUtil.StringCallback callback){
        FormEncodingBuilder builder = HttpHeader.initFormEncodingBuilder();
        builder.add("menu",menu);
        builder.add("key",key);
        OkHttpUtil.getInstanse().postAsyn(url,builder,callback);
    }

    public static void forecast(String url, String cityname, String key, OkHttpUtil.StringCallback callback){
        FormEncodingBuilder builder = HttpHeader.initFormEncodingBuilder();
        builder.add("cityname",cityname);
        builder.add("key",key);
        OkHttpUtil.getInstanse().postAsyn(url,builder,callback);
    }
}
