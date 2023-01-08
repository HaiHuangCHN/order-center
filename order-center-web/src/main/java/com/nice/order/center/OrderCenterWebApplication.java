package com.nice.order.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableCaching // or you can add this annotation in configuration class
@MapperScan(value = "com.nice.order.center.dao.mapper")
public class OrderCenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterWebApplication.class, args);
    }

}
