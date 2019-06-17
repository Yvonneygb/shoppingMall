package com.wyu.infolib.service;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.entity.Attendance;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 17:21
 * @Version 1.0
 */
public interface AttendanceService {
    //保存日志
    int save(Attendance attendance);
    //获得日志list
    List<Attendance> logList(PageVO pageVO);
    //删除考勤日志
    void deleteLog(String ids);
    //更新考勤日志
    void updateLog(Attendance attendance);
    //找到设备上次打卡的时间
    Attendance findLastLog(Integer id, int userId);
    //找到时间段打卡日志
    List<Attendance> findLogs(Date startDate, Date endDate, int userId);

    PageVO logListPage(PageVO pageVO);
}
