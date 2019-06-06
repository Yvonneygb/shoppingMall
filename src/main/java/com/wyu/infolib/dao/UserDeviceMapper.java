package com.wyu.infolib.dao;

import com.wyu.infolib.entity.UserDevice;

import java.util.List;

public interface UserDeviceMapper {
    int insert(UserDevice record);

    int insertSelective(UserDevice record);

    UserDevice find(Integer deviceId, int userId);

    List<UserDevice> findByUserId(int userId);

    void delete(UserDevice userDevice);
}