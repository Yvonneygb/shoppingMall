package com.wyu.infolib.dao;

import com.wyu.infolib.entity.Alerts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlertsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Alerts record);

    int insertSelective(Alerts record);

    Alerts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Alerts record);

    int updateByPrimaryKey(Alerts record);

    List<Alerts> getList(@Param("askUserId") int askUserId);

    List<Alerts> findByAskIdR(@Param("askId") int askId, @Param("type") int type);
}