package com.wyu.infolib.service;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.entity.Communication;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 22:16
 * @Version 1.0
 */
public interface CommunicationService {
    //获得交流贴的list
    List<Communication> communicationList(PageVO pageVO);
    //保存交流貼
    void save(Communication communication);
    //更新交流贴
    void updateCommunication(Communication communication);
    //删除交流贴 s
    void deleteCommunication(String ids);
    //通过id找到实体
    Communication findById(int id);
    //微信显示的交流贴
    List<Communication> getListPageWx(PageVO pageVO);
    //通过id和分页
    List<Communication> getMyPostPage(PageVO pageVO, int userId);
    //查找关键字
    List<Communication> getKeyPage(PageVO pageVO, String keyWord);
    //分页总条数
    PageVO communicationListPage(PageVO pageVO);
}
