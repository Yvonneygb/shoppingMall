package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.common.utils.MD5Util;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description 超级管理员
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/2 9:58
 * @Version 1.0
 */
@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @PostMapping("/admin/login")
    @ResponseBody
    public ResponseVO login(String account, String password, HttpSession session){
        ResponseVO responseVO = new ResponseVO();
        UserInfo userInfo = userService.findByAccount(account);
        if(userInfo == null || !MD5Util.getSaltVerifyMD5(password, userInfo.getUserPassword())){
            responseVO.setEmsg("账号不存在或者密码错误，请重试");
            responseVO.setEcode(1);
            return responseVO;
        }
        System.out.println(userInfo.getUserType()+"}}}}}");
        if(userInfo.getUserType() != 0){
            responseVO.setEmsg("账号无权限");
            responseVO.setEcode(1);
            return responseVO;
        }
        session.setAttribute("admin", userInfo);
        responseVO.setEmsg("登陆成功");
        return responseVO;
    }
}
