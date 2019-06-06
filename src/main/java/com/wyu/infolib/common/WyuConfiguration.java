package com.wyu.infolib.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/31 17:03
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "wx")
public class WyuConfiguration {
    private String APPID;

    private String SECRET;

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getSECRET() {
        return SECRET;
    }

    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }
}
