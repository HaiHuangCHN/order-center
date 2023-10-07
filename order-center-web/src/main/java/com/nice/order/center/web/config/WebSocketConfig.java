package com.nice.order.center.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {


    /**
     * 注入 ServerEndpointExporter
     * 这个 bean 会自动注册使用了 @ServerEndpoint 注解声明的 Web Socket Endpoint
     * 要注意，如果使用独立的 Servlet 容器，而不是直接使用 Spring Boot 的内置容器，
     * 就不要注入 ServerEndpointExporter，因为它将由容器自己提供和管理。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}
