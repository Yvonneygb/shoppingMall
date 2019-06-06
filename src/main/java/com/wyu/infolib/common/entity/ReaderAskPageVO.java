package com.wyu.infolib.common.entity;

/**
 * @Description 读者提问分页条件对象
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 9:59
 * @Version 1.0
 */
public class ReaderAskPageVO {
    //第几页
    private int pageNum = 1;
    //每页多少数据
    private int pageSize = 10;
    //是否显示 ,1是显示，0是不显示,输入-2是读取全部
    private int isShow = -1;
    //是否回复
    private int isReply = -1;
    //回复的id
    private int replyId = -1;
    //提问的id
    private int submitId = -1;
    //主题
    private String theme;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getIsReply() {
        return isReply;
    }

    public void setIsReply(int isReply) {
        this.isReply = isReply;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "ReaderAskPageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", isShow=" + isShow +
                ", isReply=" + isReply +
                ", replyId=" + replyId +
                ", submitId=" + submitId +
                ", theme='" + theme + '\'' +
                '}';
    }
}
