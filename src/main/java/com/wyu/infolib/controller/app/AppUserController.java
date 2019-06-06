package com.wyu.infolib.controller.app;

import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.common.utils.MD5Util;
import com.wyu.infolib.entity.UserInfo;
import com.wyu.infolib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/1 22:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/app/user")
public class AppUserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseVO userLogin(String account, String password){
        ResponseVO responseVO = new ResponseVO();
        UserInfo userInfo = userService.findByAccount(account);
        if(userInfo != null) {
            String pass = userInfo.getUserPassword();
            System.out.println(pass);
            if(MD5Util.getSaltVerifyMD5(password, pass)){
                userInfo.setUserPassword(null);
                responseVO.setEcode(0);
                responseVO.setEmsg("登录成功");
                responseVO.setData(userInfo);
                return responseVO;
            }
        }
        responseVO.setEcode(1);
        responseVO.setEmsg("用户名或者密码错误");
        return responseVO;
    }
}
