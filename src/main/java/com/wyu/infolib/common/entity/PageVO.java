package com.wyu.infolib.common.entity;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/4 0:23
 * @Version 1.0
 */
public class PageVO<T> {
    /** 第几页 */
    private int pageNum = 1;

    /** 每页数据 */
    private int pageSize = 10;

    /**
     * 总条数
     */
    private long total;
    /**
     * 数据结合    T就代表未知类型
     */
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

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
