package com.wyu.infolib.controller.platform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.common.entity.UDRelationVO;
import com.wyu.infolib.common.utils.MD5Util;
import com.wyu.infolib.entity.AttendanceDevice;
import com.wyu.infolib.entity.UserDevice;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.AttendanceDeviceService;
import com.wyu.infolib.service.UserDeviceService;
import com.wyu.infolib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 考勤人员信息
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 10:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/kqUser")
public class AttendanceController {

    @Resource
    UserService userService;

    @Resource
    UserDeviceService userDeviceService;

    @Resource
    AttendanceDeviceService attendanceDeviceService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO userList(PageVO pageVO) {
        ResponseVO responseVO = new ResponseVO();
        List<UserInfo> list = userService.findListHasAccount(1, pageVO);//1有账号
        responseVO.setEmsg("获取成功");
        responseVO.setData(list);
        return responseVO;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addUser(UserInfo user) {
        ResponseVO responseVO = new ResponseVO();
        UserInfo isUser = userService.findByAccount(user.getUserAccount());//判断账号是否存在
        if (isUser != null) {
            responseVO.setEcode(1);
            responseVO.setEmsg("账号已经存在了，请重试");
        } else {
            String password = user.getUserPassword();
            user.setUserPassword(MD5Util.getSaltMD5(password));//加密
            user.setRegTime(new Timestamp(System.currentTimeMillis()));
            try {
                userService.save(user);
                responseVO.setEcode(0);
                responseVO.setEmsg("新增用户成功");
            } catch (Exception e) {
                e.printStackTrace();
                responseVO.setEcode(1);
                responseVO.setEmsg("新增用户失败");
            }
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateUser(UserInfo user) {
        ResponseVO responseVO = new ResponseVO();
        try {
            userService.updateUser(user);
            responseVO.setEmsg("信息更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("信息更新失败");
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteUsers(String userIds) {
        ResponseVO responseVO = new ResponseVO();
        try {
            userService.deleteUsers(userIds);
            responseVO.setEcode(0);
            responseVO.setEmsg("删除用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("删除用户失败");
        }
        return responseVO;
    }

    //修改登陆密码
    @PostMapping("/setPassword")
    @ResponseBody
    public ResponseVO setPassword(int userId, String newPassword) {
        ResponseVO responseVO = new ResponseVO();
        UserInfo userInfo = userService.findById(userId);
        userInfo.setUserPassword(MD5Util.getSaltMD5(newPassword));
        try {
            userService.updateUser(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    //获取用户设备关系数据
    @PostMapping("/getUserDeviceRelation")
    @ResponseBody
    public ResponseVO getUserDeviceRelation(int userId) {
        ResponseVO responseVO = new ResponseVO();
        List<AttendanceDevice> attendanceDevices = attendanceDeviceService.findList();
        List<UserDevice> userDevices = userDeviceService.findByUserId(userId);
        List<UDRelationVO> udRelationVOS = new ArrayList<>();
        for (int i = 0; i < attendanceDevices.size(); i++) {
            UDRelationVO udRelationVO = new UDRelationVO();
            udRelationVO.setAttendancePlace(attendanceDevices.get(i).getAttendancePlace());
            udRelationVO.setDeviceId(attendanceDevices.get(i).getId());
            udRelationVO.setDeviceNum(attendanceDevices.get(i).getDeviceId());
            for (int j = 0; j < userDevices.size(); j++) {
                if (attendanceDevices.get(i).getId() == userDevices.get(j).getDeviceId()) {
                    udRelationVO.setChoose(true);
                    break;
                }
            }
            udRelationVOS.add(udRelationVO);
        }
        responseVO.setData(udRelationVOS);
        return responseVO;
    }

    //更新用户设备关系数据
    @PostMapping("/updateUserDeviceRelation")
    @ResponseBody
    public ResponseVO updateUserDeviceRelation(@RequestBody Map<String,Object> reqMap) {
        int userId = Integer.valueOf(reqMap.get("userId").toString());
        //用fastJson做格式转换
        String rvs = JSONArray.toJSONString(reqMap.get("relationVOS"));
        List<UDRelationVO> relationVOS = JSON.parseArray(rvs, UDRelationVO.class);
        ResponseVO responseVO = new ResponseVO();
        for (int i = 0; i < relationVOS.size(); i++) {
            int deviceId = relationVOS.get(i).getDeviceId();
            UserDevice userDevice = userDeviceService.find(deviceId, userId);
            if(relationVOS.get(i).isChoose()){
                if (userDevice == null){
                    UserDevice vo = new UserDevice();
                    vo.setDeviceId(deviceId);
                    vo.setUserId(userId);
                    userDeviceService.save(vo);
                }
            }else {
                if (userDevice != null) {
                    userDeviceService.delete(userDevice);
                }
            }
        }
        return responseVO;
    }
}
