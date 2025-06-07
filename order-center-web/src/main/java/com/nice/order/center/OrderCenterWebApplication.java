package com.nice.order.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.nice.order.center.dao.mapper")
// 加此注解
@EnableDiscoveryClient
public class OrderCenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterWebApplication.class, args);
    }

}
