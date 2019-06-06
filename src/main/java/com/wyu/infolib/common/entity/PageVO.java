package com.wyu.infolib.common.entity;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/4 0:23
 * @Version 1.0
 */
public class PageVO {
    /** 第几页 */
    private int pageNum = 1;

    /** 每页数据 */
    private int pageSize = 10;

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
}
