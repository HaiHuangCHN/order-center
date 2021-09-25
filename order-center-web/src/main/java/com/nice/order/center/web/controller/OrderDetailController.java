package com.nice.order.center.web.controller;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.service.OrderDetailService;
import com.nice.order.center.service.dto.OrderDetailDTO;
import com.nice.order.center.web.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(value = "/get/order/{userId}")
    public ResponseEntity<OrderDetailVO> createOrder(@PathVariable("userId") Long userId) {
        OrderDetailDTO dto = orderDetailService.findOrderDetailByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ModelMapperUtil.getModelMapperWithFieldMatching().map(dto, OrderDetailVO.class));
    }

}
