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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(value = "/queryOrderByUserNo/{userNo}")
    public ResponseEntity<OrderDetailResVO> getOrder(HttpServletRequest request,
                                                     @PathVariable("userNo") String userNo) {
        log.info("servletPath：{}", request.getServletPath());
        log.info("userId：{}", request.getParameter("userNo"));
        log.info("arbitrary：{}", request.getParameter("arbitrary"));
        OrderDetailResDTO resDto = orderDetailService.findOrderDetailByUserNo(userNo);
        OrderDetailResVO resVo = ModelMapperUtil.getModelMapperWithFieldMatching().map(resDto, OrderDetailResVO.class);
        return ResponseEntity.status(HttpStatus.OK).body(resVo);
    }

    @PostMapping(value = "/create/order")
    public ResponseEntity<String> createOrder(OrderDetailReqVO orderDetailReqVO) {
        OrderDetailReqDTO orderDetailReqDto = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqVO,
                OrderDetailReqDTO.class);
        // TODO Get the user from session instead of hard code
        String orderId = orderDetailService.addNewOrder(orderDetailReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(orderId);
    }

}
