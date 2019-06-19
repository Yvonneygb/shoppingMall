package com.wyu.infolib.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 管理员登陆拦截器
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/6/19 23:06
 * @Version 1.0
 */
@Configuration
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //得到session
        HttpSession session = httpServletRequest.getSession();
        // 得到对象
        Object admin = session.getAttribute("admin");
        // 判断对象是否存在
        if(admin!=null){
            return true;
        }else{
            httpServletResponse.sendRedirect("/errors");
            httpServletRequest.setAttribute("msg","没有权限请先登陆");
            return false;
        }
    }
}
