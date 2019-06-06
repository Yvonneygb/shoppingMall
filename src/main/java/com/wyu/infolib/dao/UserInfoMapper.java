package com.wyu.infolib.dao;

import com.wyu.infolib.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo findByAccount(String account);

    UserInfo findByOpenid(String openid);

    UserInfo findByIdCard(String idCard);

    List<UserInfo> findListHasAccount(@Param("flag") int flag);
}