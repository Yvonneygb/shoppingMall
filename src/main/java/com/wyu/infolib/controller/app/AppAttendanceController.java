package com.wyu.infolib.controller.app;

import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.dao.UserInfoMapper;
import com.wyu.infolib.entity.Attendance;
import com.wyu.infolib.entity.AttendanceDevice;
import com.wyu.infolib.entity.UserDevice;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.AttendanceDeviceService;
import com.wyu.infolib.service.AttendanceService;
import com.wyu.infolib.service.UserDeviceService;
import com.wyu.infolib.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description app考勤打卡
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/1 23:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/app/attendance")
public class AppAttendanceController {

    @Resource
    AttendanceService attendanceService;

    @Resource
    AttendanceDeviceService attendanceDeviceService;

    @Resource
    UserService userService;

    @Resource
    UserDeviceService userDeviceService;

    //时间间隔
    public static final int NEED_TIME = 40;

    //打卡
    @PostMapping("/clockIn")
    public ResponseVO clockIn(String deviceId, int userId){
        ResponseVO responseVO = new ResponseVO();
        AttendanceDevice attendanceDevice = attendanceDeviceService.findByDeviceId(deviceId);
        if(attendanceDevice == null){
            responseVO.setEcode(1);
            responseVO.setEmsg("找不到设备，请联系管理员处理");
            return responseVO;
        }
        UserDevice userDevice = userDeviceService.find(attendanceDevice.getId(),userId);
        if (userDevice == null) {
            responseVO.setEcode(1);
            responseVO.setEmsg("此设备不在您考勤范围内");
            return responseVO;
        }
        Attendance oldAttendanceLog = attendanceService.findLastLog(attendanceDevice.getId(), userId);
        //检查当前是否可以打卡
        if (oldAttendanceLog != null) {
            ResponseVO canAttendance = checkCanAttendance(oldAttendanceLog.getAttendanceTime());
            System.out.println(canAttendance.toString());
            if (canAttendance.getEcode() == 1) {
                return canAttendance;
            }
        }
        UserInfo userInfo = userService.selectByPrimaryKey(userId);
        System.out.println(userInfo.toString());
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);//用户id
        attendance.setUserIdCard(userInfo.getUserIdCard());//用户工号
        attendance.setUserName(userInfo.getUserName());//用户真名字
        attendance.setAttendanceDeviceId(attendanceDevice.getId());//设备id
        attendance.setAttendancePlace(attendanceDevice.getAttendancePlace());//打卡地点
        attendance.setAttendanceTime(new Timestamp(System.currentTimeMillis()));//打卡时间
        attendanceService.save(attendance);
        responseVO.setEcode(0);
        responseVO.setEmsg("打卡成功");
        return responseVO;
    }

    //获取当前时段打卡的数据
    @PostMapping("/clockData")
    public ResponseVO clockData(Date date, int userId){
        ResponseVO responseVO = new ResponseVO();
        List<Attendance> attendances = getClockOk(date, userId);
        List<AttendanceDevice> attendanceDevices = getClockNo(date, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("clockOk",attendances);
        map.put("clockNo",attendanceDevices);
        responseVO.setData(map);
        return responseVO;
    }

    //找到当前已经打卡的设备
    public List<Attendance> getClockOk(Date date, int userId){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);//把分去掉
        calendar.set(Calendar.SECOND, 0);//把秒去掉
        Date startDate = calendar.getTime();//转成开始时间
        int hour = calendar.get(Calendar.HOUR_OF_DAY) + 1;
        if(hour > 23){
            int day = calendar.get(Calendar.DATE) + 1;//超过24日期加一 小时置0
            calendar.set(Calendar.DATE, day);
            hour = 0;
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        Date endDate = calendar.getTime();//结束时间
        List<Attendance> list = attendanceService.findLogs(startDate, endDate, userId);
        return list;
    }

    //找到未打卡设备
    public List<AttendanceDevice> getClockNo(Date date, int userId){
        List<Attendance> list = getClockOk(date, userId);
        List<UserDevice> userDeviceList = userDeviceService.findByUserId(userId);
        List<AttendanceDevice> attendanceDeviceList = new ArrayList<>();
        for (int i = 0; i < userDeviceList.size(); i++) {
            int flag = 1;
            for (int j = 0; j < list.size(); j++) {
                if(userDeviceList.get(i).getDeviceId() == list.get(j).getAttendanceDeviceId()){
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                AttendanceDevice attendanceDevice = attendanceDeviceService.findById(userDeviceList.get(i).getDeviceId());
                attendanceDeviceList.add(attendanceDevice);
            }
        }
        return attendanceDeviceList;
    }

    //检查当前是否能打卡
    public ResponseVO checkCanAttendance(Date timestamp){
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());//系统当前时间
        ResponseVO responseVO = new ResponseVO();
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        cal.setTime(nowTime);
        int nowMonth = cal.get(Calendar.MONTH) + 1;
        int nowDay = cal.get(Calendar.DATE);
        int nowHour = cal.get(Calendar.HOUR_OF_DAY);
        int nowMinute = cal.get(Calendar.MINUTE);
        System.out.printf("数据时间%d/%d %d:%d\n",month,day,hour,minute);
        System.out.printf("现在时间%d/%d %d:%d\n",nowMonth,nowDay,nowHour,nowMinute);
        if (month == nowMonth) {
            if (day == nowDay) {
                if (hour == nowHour) {
                    responseVO.setEmsg("您在这时段已经打过卡了");
                    responseVO.setEcode(1);
                }else {
                    int poorTime = 60 - minute;
                    poorTime = poorTime + nowMinute;//算出时间差距
                    if(poorTime < NEED_TIME){
                        String msg = "您需要等待" + (NEED_TIME - poorTime) + "分钟，才能进行下次打卡";
                        responseVO.setEmsg(msg);
                        responseVO.setEcode(1);
                    }
                }
            }
        }
        return responseVO;
    }
}
