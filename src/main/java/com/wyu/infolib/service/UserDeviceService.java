package com.wyu.infolib.service;

import com.wyu.infolib.entity.UserDevice;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/5 17:22
 * @Version 1.0
 */
public interface UserDeviceService {
    //找到对应关系
    UserDevice find(Integer deviceId, int userId);
    //找到用户需要打卡设备list
    List<UserDevice> findByUserId(int userId);
    //保存
    void save(UserDevice vo);
    //删除
    void delete(UserDevice userDevice);
}
