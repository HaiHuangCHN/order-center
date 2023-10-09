package com.nice.order.center.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket 配置类。开启 WebSocket 的支持
 */
@Configuration
public class WebSocketConfig {

    /**
     * bean注册：会自动扫描带有 @ServerEndpoint 注解声明的 Websocket Endpoint(端点)，注册成为 Websocket bean。
     * 要注意，如果项目使用外置的servlet容器，而不是直接使用 Spring Boot 内置容器的话，就不要注入 ServerEndpointExporter，因为它将由容器自己提供和管理。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
