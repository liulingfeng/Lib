package com.llf.lib.mvp.data.entity;

/**
 * Created by llf on 2016/8/5.
 * 基础的实体类
 */
public class BaseEntity<T> {
    //返回值code，这个需要和服务端定义好
    public int resultcode;
    //返回结果信息
    public String reason;
    public T result;
}
