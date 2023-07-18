package com.nice.order.center.web.controller;

import com.nice.order.center.dao.entity.mongo.OrderDetail;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import com.nice.order.center.service.service.order.OrderDetailMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author hai.huang.a@outlook.com
 * @date 2022/3/24 17:36
 * @deprecated 仅供测试使用
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@Deprecated
@RequestMapping("mongo/test")
public class MongoController {

    private final OrderDetailMongoService orderDetailMongoService;

    private final MongoTemplate mongoTemplate;

    @GetMapping("/repository")
    @ResponseBody
    public OrderDetailResDTO repository(@RequestParam("userNo") String userNo) {
        return orderDetailMongoService.findOrderDetailByUserNo(userNo);
    }

    @GetMapping("/mongo/template")
    @ResponseBody
    public List<OrderDetail> mongoTemplate(@RequestParam("userNo") String userNo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userNo").is(userNo));
        return mongoTemplate.find(query, OrderDetail.class);
    }

}