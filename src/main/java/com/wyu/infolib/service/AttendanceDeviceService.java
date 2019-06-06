package com.wyu.infolib.service;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.entity.AttendanceDevice;

import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 17:31
 * @Version 1.0
 */
public interface AttendanceDeviceService {
    //根据设备唯一id找到设备
    AttendanceDevice findByDeviceId(String deviceId);
    //拿到设备集合，分页
    List<AttendanceDevice> findList(PageVO pageVO);
    //新增数据
    void add(AttendanceDevice device);
    //更新数据
    void update(AttendanceDevice device);
    //删除数据
    void deleteDevice(String ids);
    //通过主键 找到设备
    AttendanceDevice findById(Integer id);
    //拿到设备集合
    List<AttendanceDevice> findList();
}
