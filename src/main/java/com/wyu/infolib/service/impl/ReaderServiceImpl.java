package com.wyu.infolib.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ReaderAskPageVO;
import com.wyu.infolib.dao.ReaderCommentsMapper;
import com.wyu.infolib.entity.ReaderComments;
import com.wyu.infolib.service.ReaderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 10:27
 * @Version 1.0
 */
@Service
public class ReaderServiceImpl implements ReaderService {

    @Resource
    ReaderCommentsMapper readerCommentsMapper;

    @Override
    public List<ReaderComments> getListPage(ReaderAskPageVO readerAskPageVO) {
        PageHelper.startPage(readerAskPageVO.getPageNum(),readerAskPageVO.getPageSize(),false,false,false);
        return readerCommentsMapper.getListPage(readerAskPageVO);
    }

    @Override
    public ReaderComments findById(int askId) {
        return readerCommentsMapper.selectByPrimaryKey(askId);
    }

    @Override
    public int updateInfo(ReaderComments readerComments) {
        return readerCommentsMapper.updateByPrimaryKeySelective(readerComments);
    }

    @Override
    public int save(ReaderComments readerComments) {
        return readerCommentsMapper.insertSelective(readerComments);
    }

    @Override
    public void deleteReader(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for (int i = 0; i < list.size(); i++) {
            readerCommentsMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    @Override
    public List<ReaderComments> getMyAskPage(PageVO pageVO, int userId) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        List<ReaderComments> list = readerCommentsMapper.getMyAskPage(userId);
        return list;
    }

    @Override
    public List<ReaderComments> getKeyPage(PageVO pageVO, String keyWord) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        List<ReaderComments> list = readerCommentsMapper.getKeyPage(keyWord);
        return list;
    }

}
