package com.wyu.infolib.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReaderComments {
    private Integer id;

    private Integer useId;

    private String msgTheme;

    private String msgSubmitContent;

    private String msgImgUrls;

    private List<String> imgList;

    private String msgSubmit;

    private String msgSubmitAvatar;

    private Integer msgSubmitId;

    private Date msgSubmitTime;

    private String msgReplyContent;

    private String msgReply;

    private String msgReplyAvatar;

    private Integer msgReplyId;

    private Date msgReplyTime;

    private Integer msgShow = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public String getMsgTheme() {
        return msgTheme;
    }

    public void setMsgTheme(String msgTheme) {
        this.msgTheme = msgTheme == null ? null : msgTheme.trim();
    }

    public String getMsgSubmitContent() {
        return msgSubmitContent;
    }

    public void setMsgSubmitContent(String msgSubmitContent) {
        this.msgSubmitContent = msgSubmitContent == null ? null : msgSubmitContent.trim();
    }

    public String getMsgSubmit() {
        return msgSubmit;
    }

    public void setMsgSubmit(String msgSubmit) {
        this.msgSubmit = msgSubmit == null ? null : msgSubmit.trim();
    }

    public String getMsgSubmitAvatar() {
        return msgSubmitAvatar;
    }

    public void setMsgSubmitAvatar(String msgSubmitAvatar) {
        this.msgSubmitAvatar = msgSubmitAvatar == null ? null : msgSubmitAvatar.trim();
    }

    public Integer getMsgSubmitId() {
        return msgSubmitId;
    }

    public void setMsgSubmitId(Integer msgSubmitId) {
        this.msgSubmitId = msgSubmitId;
    }

    public Date getMsgSubmitTime() {
        return msgSubmitTime;
    }

    public void setMsgSubmitTime(Date msgSubmitTime) {
        this.msgSubmitTime = msgSubmitTime;
    }

    public String getMsgReplyContent() {
        return msgReplyContent;
    }

    public void setMsgReplyContent(String msgReplyContent) {
        this.msgReplyContent = msgReplyContent == null ? null : msgReplyContent.trim();
    }

    public String getMsgReply() {
        return msgReply;
    }

    public void setMsgReply(String msgReply) {
        this.msgReply = msgReply == null ? null : msgReply.trim();
    }

    public String getMsgReplyAvatar() {
        return msgReplyAvatar;
    }

    public void setMsgReplyAvatar(String msgReplyAvatar) {
        this.msgReplyAvatar = msgReplyAvatar == null ? null : msgReplyAvatar.trim();
    }

    public Integer getMsgReplyId() {
        return msgReplyId;
    }

    public void setMsgReplyId(Integer msgReplyId) {
        this.msgReplyId = msgReplyId;
    }

    public Date getMsgReplyTime() {
        return msgReplyTime;
    }

    public void setMsgReplyTime(Date msgReplyTime) {
        this.msgReplyTime = msgReplyTime;
    }

    public Integer getMsgShow() {
        return msgShow;
    }

    public void setMsgShow(Integer msgShow) {
        this.msgShow = msgShow;
    }

    public String getMsgImgUrls() {
        return msgImgUrls;
    }

    public void setMsgImgUrls(String msgImgUrls) {
        this.msgImgUrls = msgImgUrls;
    }

    public List<String> getImgList() {
        List<String> list;
        if (msgImgUrls != null){
            list = Arrays.asList(msgImgUrls.split(","));
            return list;
        }
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    @Override
    public String toString() {
        return "ReaderComments{" +
                "id=" + id +
                ", useId=" + useId +
                ", msgTheme='" + msgTheme + '\'' +
                ", msgSubmitContent='" + msgSubmitContent + '\'' +
                ", msgImgUrls='" + msgImgUrls + '\'' +
                ", imgList=" + imgList +
                ", msgSubmit='" + msgSubmit + '\'' +
                ", msgSubmitAvatar='" + msgSubmitAvatar + '\'' +
                ", msgSubmitId=" + msgSubmitId +
                ", msgSubmitTime=" + msgSubmitTime +
                ", msgReplyContent='" + msgReplyContent + '\'' +
                ", msgReply='" + msgReply + '\'' +
                ", msgReplyAvatar='" + msgReplyAvatar + '\'' +
                ", msgReplyId=" + msgReplyId +
                ", msgReplyTime=" + msgReplyTime +
                ", msgShow=" + msgShow +
                '}';
    }
}