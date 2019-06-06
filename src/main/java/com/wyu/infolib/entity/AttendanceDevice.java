package com.wyu.infolib.entity;

import java.util.Date;

public class AttendanceDevice {
    private Integer id;

    private String deviceId;

    private String deviceName;

    private String deviceVersion;

    private String deviceOnlyNum;

    private String remark;

    private String attendancePlace;

    private String installPlace;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

    public String getDeviceOnlyNum() {
        return deviceOnlyNum;
    }

    public void setDeviceOnlyNum(String deviceOnlyNum) {
        this.deviceOnlyNum = deviceOnlyNum == null ? null : deviceOnlyNum.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAttendancePlace() {
        return attendancePlace;
    }

    public void setAttendancePlace(String attendancePlace) {
        this.attendancePlace = attendancePlace == null ? null : attendancePlace.trim();
    }

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace == null ? null : installPlace.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}