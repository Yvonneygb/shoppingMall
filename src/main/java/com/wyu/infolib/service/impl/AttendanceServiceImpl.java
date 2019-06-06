package com.wyu.infolib.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.dao.AttendanceMapper;
import com.wyu.infolib.entity.Attendance;
import com.wyu.infolib.service.AttendanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/3/30 17:21
 * @Version 1.0
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Resource
    AttendanceMapper attendanceMapper;

    @Override
    public int save(Attendance attendance) {
        return attendanceMapper.insertSelective(attendance);
    }

    @Override
    public List<Attendance> logList(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return attendanceMapper.logList();
    }

    @Override
    public void deleteLog(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for (int i = 0; i < list.size(); i++) {
            attendanceMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    @Override
    public void updateLog(Attendance attendance) {
        attendanceMapper.updateByPrimaryKeySelective(attendance);
    }

    @Override
    public Attendance findLastLog(Integer id, int userId) {
        List<Attendance> list = attendanceMapper.findLastLog(id, userId);
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Attendance> findLogs(Date startDate, Date endDate, int userId) {
        return attendanceMapper.findLogs(startDate, endDate, userId);
    }
}
