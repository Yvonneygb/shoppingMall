package com.wyu.infolib.common.entity;

import java.io.Serializable;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/6 11:35
 * @Version 1.0
 */
public class UDRelationVO implements Serializable {
    /** 是否存在关系 */
    private boolean isChoose = false;

    /** 设备id */
    private int deviceId;

    /** 设备考勤地点  */
    private String attendancePlace;

    /** 设备编号  */
    private String deviceNum;

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getAttendancePlace() {
        return attendancePlace;
    }

    public void setAttendancePlace(String attendancePlace) {
        this.attendancePlace = attendancePlace;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
}
