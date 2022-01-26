package com.nice.order.center.web.controller;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.service.OrderDetailService;
import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import com.nice.order.center.web.vo.req.OrderDetailReqVO;
import com.nice.order.center.web.vo.res.OrderDetailResVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(value = "/get/order/{userId}")
    public ResponseEntity<OrderDetailResVO> getOrder(@PathVariable("userId") Long userId) {
        OrderDetailResDTO dto = orderDetailService.findOrderDetailByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(dto, OrderDetailResVO.class));
    }

//    @PostMapping(value = "/new/order")
//    public ResponseEntity<String> createOrder(OrderDetailReqVO orderDetailReqVO) {
//        OrderDetailReqDTO orderDetailReqDto = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqVO, OrderDetailReqDTO.class);
//        // TODO Get the user from session instead of hard code
//        String orderId = orderDetailService.addNewOrder(orderDetailReqDto);
//        return ResponseEntity.status(HttpStatus.OK).body(orderId);
//    }

}
