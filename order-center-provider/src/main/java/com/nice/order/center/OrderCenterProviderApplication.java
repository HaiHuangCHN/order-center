package com.nice.order.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 加此注解
@EnableDiscoveryClient
@MapperScan(value = "com.nice.order.center.dao.mapper")
public class OrderCenterProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterProviderApplication.class, args);
    }

}
