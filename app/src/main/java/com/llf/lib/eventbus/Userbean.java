package com.llf.lib.eventbus;

/**
 * Created by llf on 2016/7/28.
 *
 */
public class Userbean extends BaseResponse {
    private String userName;
    private String age;
    private String information;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "用户名:" + userName + "年龄:" + age + "个性签名:" + information;
    }
}
