package com.nice.order.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.nice.order.center.dao.mapper")
public class OrderCenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterWebApplication.class, args);
    }

}
