package com.wyu.infolib.dao;

import com.wyu.infolib.common.entity.ReaderAskPageVO;
import com.wyu.infolib.entity.ReaderComments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReaderCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReaderComments record);

    int insertSelective(ReaderComments record);

    ReaderComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReaderComments record);

    int updateByPrimaryKey(ReaderComments record);

    List<ReaderComments> getListPage(ReaderAskPageVO readerAskPageVO);

    List<ReaderComments> getMyAskPage(@Param("userId") int userId);

    List<ReaderComments> getKeyPage(@Param("keyWord")String keyWord);
}