package com.wyu.infolib.common.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 16:18
 * @Version 1.0
 */
public class AlertsShowVO {

    //消息类型，1是提问，2是交流
    private int postType;

    //原贴id
    private int postId;

    //消息类id
    private int alertId;

    private String replyName;

    private String replyAvatarUrl;

    private String replyContent;

    private Date replyTime;

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyAvatarUrl() {
        return replyAvatarUrl;
    }

    public void setReplyAvatarUrl(String replyAvatarUrl) {
        this.replyAvatarUrl = replyAvatarUrl;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    @Override
    public String toString() {
        return "AlertsShowVO{" +
                "postType=" + postType +
                ", postId=" + postId +
                ", alertId=" + alertId +
                ", replyName='" + replyName + '\'' +
                ", replyAvatarUrl='" + replyAvatarUrl + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                '}';
    }
}
