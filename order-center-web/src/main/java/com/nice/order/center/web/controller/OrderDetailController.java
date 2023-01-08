package com.nice.order.center.web.controller;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.service.OrderDetailService;
import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import com.nice.order.center.web.vo.req.OrderDetailReqVO;
import com.nice.order.center.web.vo.res.OrderDetailResVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping(value = "/queryByUserNo/{userNo}")
    public ResponseEntity<OrderDetailResVO> getOrder(HttpServletRequest request,
                                                     @PathVariable("userNo") String userNo) {
        log.info("请求时间[{}]，接口URL[{}]，接口方法[{}]，调用结果[{}]，执行时间[{}]", LocalDateTime.now(), "", "", "", "");
        log.info("请求时间[{}]，接口URL[{}]，接口方法[{}]，调用结果[{}]，执行时间[{}]", LocalDateTime.now(), "", "", "", "");
        log.info("servletPath：{}", request.getServletPath());
        log.info("userId：{}", request.getParameter("userNo"));
        log.info("arbitrary：{}", request.getParameter("arbitrary"));
        OrderDetailResDTO resDto = orderDetailService.findOrderDetailByUserNoWithCache(userNo);
        OrderDetailResVO resVo = ModelMapperUtil.getModelMapperWithFieldMatching().map(resDto, OrderDetailResVO.class);
        return ResponseEntity.status(HttpStatus.OK).body(resVo);
    }

    // TODO Get the user from session instead of parameter
    @PostMapping(value = "order/create")
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderDetailReqVO orderDetailReqVO) {
        OrderDetailReqDTO orderDetailReqDto = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqVO,
                OrderDetailReqDTO.class);
        boolean result = orderDetailService.addNewOrder(orderDetailReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
