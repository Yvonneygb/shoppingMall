package com.wyu.infolib.common.entity;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/29 17:32
 * @Version 1.0
 */
public class ResponseVO<T> {
    //处理结果，int类型   0正常，1异常
    private int ecode = 0;

    //处理结果，string类型
    private String emsg;

    //处理说明，string类型
    private T data;

    public int getEcode() {
        return ecode;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
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

    @Override
    public String toString() {
        return "ResponseVO{" +
                "ecode=" + ecode +
                ", emsg='" + emsg + '\'' +
                ", data=" + data +
                '}';
    }
}
