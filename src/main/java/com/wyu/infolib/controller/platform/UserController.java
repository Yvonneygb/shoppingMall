package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/2 10:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public ResponseVO userList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            List<UserInfo> list = userService.findListHasAccount(0, pageVO);
            responseVO.setEmsg("获取成功");
            responseVO.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseVO;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addUser(UserInfo userInfo){
        ResponseVO responseVO = new ResponseVO();
        if(userInfo.getRegTime() == null){
            userInfo.setRegTime(new Timestamp(System.currentTimeMillis()));
        }
        try {
            userService.save(userInfo);
            responseVO.setEmsg("新增成功");
        } catch (Exception e) {
            responseVO.setEcode(1);
            responseVO.setEmsg("新增失败");
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateUser(UserInfo userInfo){
        ResponseVO responseVO = new ResponseVO();
        try {
            userService.updateUser(userInfo);
            responseVO.setEmsg("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("更新失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteUser(String ids){
        ResponseVO responseVO = new ResponseVO();
        try {
            userService.deleteUsers(ids);
            responseVO.setEmsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("删除失败");
        }
        return responseVO;
    }

    @PostMapping("/changeAuth")
    @ResponseBody
    public ResponseVO changeAuth(int userId,int authNum){
        ResponseVO responseVO = new ResponseVO();
        UserInfo userInfo = userService.findById(userId);
        userInfo.setAuthentication(authNum);
        try {
            userService.updateUser(userInfo);
            responseVO.setEmsg("更新成功");
        } catch (Exception e) {
            responseVO.setEcode(1);
            responseVO.setEmsg("更新失败");
        }
        return responseVO;
    }
}
