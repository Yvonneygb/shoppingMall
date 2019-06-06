package com.wyu.infolib.entity;

import java.util.Date;
import java.util.List;

public class Communication {
    private Integer id;

    private String content;

    private String imgUrls;

    private Integer likeNum;

    private Integer seeNum;

    private Integer submitUserId;

    private String nickname;

    private String avatarUrl;

    private Date msgSubmitTime;

    private Integer isShow;

    private List<Reply> replies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getSeeNum() {
        return seeNum;
    }

    public void setSeeNum(Integer seeNum) {
        this.seeNum = seeNum;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }


    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Date getMsgSubmitTime() {
        return msgSubmitTime;
    }

    public void setMsgSubmitTime(Date msgSubmitTime) {
        this.msgSubmitTime = msgSubmitTime;
    }

    @Override
    public String toString() {
        return "Communication{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                ", likeNum=" + likeNum +
                ", seeNum=" + seeNum +
                ", submitUserId=" + submitUserId +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", msgSubmitTime=" + msgSubmitTime +
                ", isShow=" + isShow +
                ", replies=" + replies +
                '}';
    }
}