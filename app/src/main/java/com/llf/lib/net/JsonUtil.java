package com.llf.lib.net;

import com.alibaba.fastjson.JSON;
import java.util.List;

/**
 * Created by llf on 2016/7/28.
 * json解析
 */
public class JsonUtil {
    public static <T> T getObject(String jsonString, Class<T> cls) {
        return JSON.parseObject(jsonString, cls);
    }

    public static <T> List<T> getObjects(String jsonString, Class<T> cls) {
        return JSON.parseArray(jsonString, cls);
    }
}
