package com.wyu.infolib.controller.platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 超级管理员
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/2 9:58
 * @Version 1.0
 */
@Controller
public class AdminController {

    @PostMapping("/admin/login")
    public String login(){

        return "";
    }
}
