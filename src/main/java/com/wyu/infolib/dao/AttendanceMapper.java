package com.wyu.infolib.dao;

import com.wyu.infolib.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    List<Attendance> logList();

    List<Attendance> findLastLog(@Param("id") Integer id,@Param("userId") int userId);

    List<Attendance> findLogs(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("userId") int userId);
}