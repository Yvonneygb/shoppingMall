package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.Attendance;
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
 * @Since 2019/4/3 10:18
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/kqLog")
public class AttendanceLogController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO logList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<Attendance> list = attendanceService.logList(pageVO);
            responseVO.setData(list);
            responseVO.setEmsg("获取成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("获取失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addLog(Attendance attendance){
        ResponseVO responseVO = new ResponseVO();
        if (attendance.getAttendanceTime() == null){
            attendance.setAttendanceTime(new Timestamp(System.currentTimeMillis()));
        }
        try {
            attendanceService.save(attendance);
            responseVO.setEmsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("新增失败");
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateLog(Attendance attendance){
        ResponseVO responseVO = new ResponseVO();
        attendanceService.updateLog(attendance);
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteLog(String ids){
        ResponseVO responseVO = new ResponseVO();
        attendanceService.deleteLog(ids);
        return responseVO;
    }


}
