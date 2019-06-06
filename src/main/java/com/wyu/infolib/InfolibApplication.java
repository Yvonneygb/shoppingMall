package com.wyu.infolib;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wyu.infolib.dao")
public class InfolibApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfolibApplication.class, args);
    }

}
