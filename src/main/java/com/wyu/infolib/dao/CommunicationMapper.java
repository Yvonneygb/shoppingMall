package com.wyu.infolib.dao;

import com.wyu.infolib.entity.Communication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Communication record);

    int insertSelective(Communication record);

    Communication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Communication record);

    int updateByPrimaryKey(Communication record);

    List<Communication> communicationList(@Param("isShow")int isShow);

    List<Communication> getMyPostPage(@Param("userId") int userId);

    List<Communication> getKeyPage(@Param("keyWord")String keyWord);
}