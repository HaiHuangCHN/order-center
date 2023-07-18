package com.nice.order.center.web.controller;

import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import com.nice.order.center.service.service.order.OrderDetailMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hai.huang.a@outlook.com
 * @date 2022/3/24 17:36
 * @deprecated 仅供测试使用
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@Deprecated
@RequestMapping("mongo")
public class MongoController {

    private final OrderDetailMongoService orderDetailMongoService;

    @GetMapping("/testQueryMongo")
    @ResponseBody
    public OrderDetailResDTO testRedisAtomicInteger(@RequestParam("userNo") String userNo) {
        return orderDetailMongoService.findOrderDetailByUserNo(userNo);
    }

}