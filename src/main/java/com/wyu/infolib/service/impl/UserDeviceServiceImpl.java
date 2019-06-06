package com.wyu.infolib.service.impl;

import com.wyu.infolib.dao.UserDeviceMapper;
import com.wyu.infolib.entity.UserDevice;
import com.wyu.infolib.service.UserDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/5 17:22
 * @Version 1.0
 */
@Service
public class UserDeviceServiceImpl implements UserDeviceService {

    @Resource
    UserDeviceMapper userDeviceMapper;

    @Override
    public UserDevice find(Integer deviceId, int userId) {
        return userDeviceMapper.find(deviceId,userId);
    }

    @Override
    public List<UserDevice> findByUserId(int userId) {
        List<UserDevice> list = userDeviceMapper.findByUserId(userId);
        if (list.size() == 0)
            return null;
        return list;
    }

    @Override
    public void save(UserDevice vo) {
        userDeviceMapper.insert(vo);
    }

    @Override
    public void delete(UserDevice userDevice) {
        userDeviceMapper.delete(userDevice);
    }
}
