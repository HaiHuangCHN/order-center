package com.nice.order.center.web.controller;

import com.nice.order.center.service.service.order.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DemoController {

    private final OrderDetailService orderDetailService;

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
