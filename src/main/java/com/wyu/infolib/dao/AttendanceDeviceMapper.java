package com.wyu.infolib.dao;

import com.wyu.infolib.entity.AttendanceDevice;

import java.util.List;

public interface AttendanceDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttendanceDevice record);

    int insertSelective(AttendanceDevice record);

    AttendanceDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttendanceDevice record);

    int updateByPrimaryKey(AttendanceDevice record);

    AttendanceDevice findByDeviceId(String deviceId);

    List<AttendanceDevice> findList();
}