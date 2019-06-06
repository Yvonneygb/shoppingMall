package com.wyu.infolib.service;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ReaderAskPageVO;
import com.wyu.infolib.entity.ReaderComments;

import java.util.List;

/**
 * @Description 读者提问相关接口
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 10:26
 * @Version 1.0
 */
public interface ReaderService {
    //获取分页数据
    List<ReaderComments> getListPage(ReaderAskPageVO readerAskPageVO);
    //通过id找到对象
    ReaderComments findById(int askId);
    //更新数据
    int updateInfo(ReaderComments readerComments);
    //保存读者提问
    int save(ReaderComments readerComments);
    //批量删除评论
    void deleteReader(String ids);
    //获取我的提问
    List<ReaderComments> getMyAskPage(PageVO pageVO, int userId);
    //搜索
    List<ReaderComments> getKeyPage(PageVO pageVO, String keyWord);
}
