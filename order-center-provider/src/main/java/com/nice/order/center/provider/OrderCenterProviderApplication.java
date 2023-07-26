package com.nice.order.center.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// 加此注解
@EnableDiscoveryClient
public class OrderCenterProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterProviderApplication.class, args);
    }

}
