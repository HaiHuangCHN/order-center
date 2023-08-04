package com.nice.order.center.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DemoController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${default.echoMsg}")
    private String defaultEchoMsg;

    @GetMapping(value = "/getServerPort")
    public String getServerPort() {
        return "Hello Nacos Discovery " + serverPort;
    }

    @GetMapping(value = "/getDefaultEchoMsg")
    public String getDefaultEchoMsg() {
        return defaultEchoMsg;
    }

}
