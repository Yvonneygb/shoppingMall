package com.wyu.infolib.service.impl;

import com.github.pagehelper.PageHelper;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.dao.AttendanceDeviceMapper;
import com.wyu.infolib.entity.AttendanceDevice;
import com.wyu.infolib.service.AttendanceDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 17:33
 * @Version 1.0
 */
@Service
public class AttendanceDeviceServiceImpl implements AttendanceDeviceService {

    @Resource
    AttendanceDeviceMapper attendanceDeviceMapper;

    @Override
    public AttendanceDevice findByDeviceId(String deviceId) {
        return attendanceDeviceMapper.findByDeviceId(deviceId);
    }


    @Override
    public List<AttendanceDevice> findList(PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize(),false,false,false);
        return attendanceDeviceMapper.findList();
    }

    @Override
    public void add(AttendanceDevice device) {
        attendanceDeviceMapper.insertSelective(device);
    }

    @Override
    public void update(AttendanceDevice device) {
        attendanceDeviceMapper.updateByPrimaryKeySelective(device);
    }

    @Override
    public void deleteDevice(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for (int i = 0; i < list.size(); i++) {
            attendanceDeviceMapper.deleteByPrimaryKey(Integer.valueOf(list.get(i)));
        }
    }

    public static void main(String[] args){
        String ids = "1";
        List<String> list = Arrays.asList(ids.split(","));
        System.out.println(list);
    }

    @Override
    public AttendanceDevice findById(Integer id) {
        return attendanceDeviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AttendanceDevice> findList() {
        return attendanceDeviceMapper.findList();
    }
}
