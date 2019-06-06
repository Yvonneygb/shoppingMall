package com.wyu.infolib.entity;

import java.util.Date;

public class Reply {
    //回复表的id
    private Integer id;
    //交流贴的id
    private Integer communicationId;
    //一级回复的id，有，就代表这条是二级评论
    private Integer replyParentId;
    //一级回复的用户名
    private String replyParentName;
    //回复人的id
    private Integer userId;
    //回复人的名字
    private String userName;
    //回复时间
    private Date replyTime;
    //是否显示
    private Integer isShow;
    //回复内容
    private String replyContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(Integer communicationId) {
        this.communicationId = communicationId;
    }

    public Integer getReplyParentId() {
        return replyParentId;
    }

    public void setReplyParentId(Integer replyParentId) {
        this.replyParentId = replyParentId;
    }

    public String getReplyParentName() {
        return replyParentName;
    }

    public void setReplyParentName(String replyParentName) {
        this.replyParentName = replyParentName == null ? null : replyParentName.trim();
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

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }
}