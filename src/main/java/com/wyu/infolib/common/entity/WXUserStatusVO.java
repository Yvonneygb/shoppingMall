package com.wyu.infolib.common.entity;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/31 17:39
 * @Version 1.0
 */
public class WXUserStatusVO<T> {
    //处理结果，int类型   0正常，1异常
    private int ecode;

    //openid
    private String openid;

    //用户状态：0数据库没有，1正常，2审核中，-1用户禁用
    private int userStatus = 0;

    //用户类型：1学生，2老师
    private int userType = -1;

    //处理结果，string类型
    private String emsg;

    //封装数据对象
    private T data;

    public int getEcode() {
        return ecode;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
