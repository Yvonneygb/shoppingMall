package com.wyu.infolib.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.dao.CommunicationMapper;
import com.wyu.infolib.dao.ReplyMapper;
import com.wyu.infolib.entity.Attendance;
import com.wyu.infolib.entity.Communication;
import com.wyu.infolib.entity.Reply;
import com.wyu.infolib.service.CommunicationService;
import com.wyu.infolib.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 22:17
 * @Version 1.0
 */
@Service
public class CommunicationServiceImpl implements CommunicationService {

    @Resource
    CommunicationMapper communicationMapper;

    @Resource
    ReplyService replyService;

    @Override
    public List<Communication> communicationList(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize(),false,false,false);
        return communicationMapper.communicationList(-1);
    }

    @Override
    public void save(Communication communication) {
        communicationMapper.insertSelective(communication);
    }

    @Override
    public void updateCommunication(Communication communication) {
        communicationMapper.updateByPrimaryKeySelective(communication);
    }

    @Override
    public void deleteCommunication(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for (int i = 0; i < list.size(); i++) {
            communicationMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    @Override
    public Communication findById(int id) {
        return communicationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Communication> getListPageWx(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize(),false,false,false);
        List<Communication> list = communicationMapper.communicationList(1);//找出显示的
        if (list.size() != 0) {
            int len = list.size();
            for (int i = 0; i < len; i++) {
                List<Reply> replyList = replyService.findByPostId(list.get(i).getId());//帖子id
                list.get(i).setReplies(replyList);
            }
        }
        return list;
    }

    @Override
    public List<Communication> getMyPostPage(PageVO pageVO, int userId) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize(), false,false,false);
        List<Communication> list = communicationMapper.getMyPostPage(userId);//找出显示的
        return list;
    }

    @Override
    public List<Communication> getKeyPage(PageVO pageVO, String keyWord) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize(), false,false,false);
        List<Communication> list = communicationMapper.getKeyPage(keyWord);
        return list;
    }

    @Override
    public PageVO communicationListPage(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<Communication> list = communicationMapper.communicationList(-1);
        PageInfo<Communication> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setPageSize(pageVO.getPageSize());
        vo.setPageNum(pageVO.getPageNum());
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }
}
