package com.wyu.infolib.entity;

import java.util.Date;

public class Attendance {
    private Integer id;

    /** 考勤设备id **/
    private Integer attendanceDeviceId;

    /** 考勤地点 **/
    private String attendancePlace;

    /** 用户id **/
    private Integer userId;

    private String userName;

    private String userIdCard;

    private Date attendanceTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttendanceDeviceId() {
        return attendanceDeviceId;
    }

    public void setAttendanceDeviceId(Integer attendanceDeviceId) {
        this.attendanceDeviceId = attendanceDeviceId;
    }

    public String getAttendancePlace() {
        return attendancePlace;
    }

    public void setAttendancePlace(String attendancePlace) {
        this.attendancePlace = attendancePlace == null ? null : attendancePlace.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}