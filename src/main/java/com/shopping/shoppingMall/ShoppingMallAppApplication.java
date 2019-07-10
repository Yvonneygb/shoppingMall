package com.shopping.shoppingMall;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shopping.shoppingMall.dao")
public class ShoppingMallAppApplication {

    public static void main(String[] args) {
            SpringApplication.run(ShoppingMallAppApplication.class, args);
        }

}
