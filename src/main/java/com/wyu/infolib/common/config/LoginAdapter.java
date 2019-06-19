package com.wyu.infolib.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Since 2019/5/3 17:40
 * @Version 1.0
 */
@Configuration
public class LoginAdapter implements WebMvcConfigurer {
    //获取拦截器的Bean
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;


    /**
     * 表示这些配置的表示静态文件所处路径， 不用拦截
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/localImg/**")
                .addResourceLocations("file:D:\\img\\");
    }

    /**
     　　* 重写addInterceptors方法
     　　* addPathPatterns：需要拦截的访问路径
     　　* excludePathPatterns：不需要拦截的路径，
     　　* String数组类型可以写多个用","分割
     　　*/
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }
}
