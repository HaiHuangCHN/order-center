package com.nice.order.center.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

/**
 * 测试控制器
 *
 * @author haihuang95@zto.com
 * @date 2023/1/18 16:09
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @Value("#{T(java.time.LocalTime).parse('${test.inject.local.time:16:30:00}')}")
    private LocalTime testInjectLocalTime;

    @GetMapping(value = "/testInjectLocalTime")
    public ResponseEntity<LocalTime> testInjectLocalTime() {
        log.info(String.valueOf(testInjectLocalTime));
        log.error("测试异常打印={}", "参数", new Exception("测试"));
        return ResponseEntity.status(HttpStatus.OK).body(testInjectLocalTime);
    }

    @GetMapping(value = "/testExceptionPrint")
    public ResponseEntity<String> testExceptionPrint() {
        log.error("测试异常打印={}", "参数", new Exception("测试"));
        return ResponseEntity.status(HttpStatus.OK).body("End");
    }

}
