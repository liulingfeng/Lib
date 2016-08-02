package com.llf.lib.mvp.data;

import com.squareup.okhttp.FormEncodingBuilder;

/**
 * Created by llf on 2016/8/2.
 * 公共的参数集成到这里
 */
public class HttpHeader {
    public static String cookingUrl = "http://apis.juhe.cn/cook/query.php";
    static {
        //这边获取公共的参数
    }
    protected static FormEncodingBuilder initFormEncodingBuilder(){
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
//        formEncodingBuilder.add("version", version);
//        formEncodingBuilder.add("imsi", ""+imsi);
//        formEncodingBuilder.add("imei", ""+imei);
        return formEncodingBuilder;
    }
}
