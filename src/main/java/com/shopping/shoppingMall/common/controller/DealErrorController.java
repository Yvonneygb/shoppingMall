package com.shopping.shoppingMall.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 错误控制类
 * @Author RLinux
 * @Email RLinux_cst@163.com
 * @Since 2019/4/30 10:40
 * @Version 1.0
 */
@Controller
public class DealErrorController {

    @RequestMapping("/errors")
    public String error(){
        System.out.println("进来");
        return "/common/error";
    }
}
