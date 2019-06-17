package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.AttendanceDevice;
import com.wyu.infolib.service.AttendanceDeviceService;
import com.wyu.infolib.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 10:20
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/kqDevice")
public class AttendanceDeviceController {

    @Autowired
    AttendanceDeviceService attendanceDeviceService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO deviceList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            PageVO vo = attendanceDeviceService.findListPage(pageVO);
            responseVO.setData(vo);
            responseVO.setEmsg("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("获取失败");
        }
        return responseVO;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addDevice(AttendanceDevice device){
        ResponseVO responseVO = new ResponseVO();
        AttendanceDevice attendanceDevice = attendanceDeviceService.findByDeviceId(device.getDeviceId());
        if (attendanceDevice != null) {
            responseVO.setEmsg("设备id已经存在了");
            responseVO.setEcode(1);
            return responseVO;
        }
        device.setCreateTime(new Timestamp(System.currentTimeMillis()));
        try {
            attendanceDeviceService.add(device);
            responseVO.setEmsg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("添加失败");
            responseVO.setEcode(0);
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateDevice(AttendanceDevice device){
        ResponseVO responseVO = new ResponseVO();
        try {
            device.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            attendanceDeviceService.update(device);
            responseVO.setEmsg("信息更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("信息更新失败");
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteDevice(String ids){
        ResponseVO responseVO = new ResponseVO();
        try {
            attendanceDeviceService.deleteDevice(ids);
            responseVO.setEmsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("删除失败");
        }
        return responseVO;
    }
}
