package com.nice.order.center.web.controller;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.service.dto.res.OrderDetailCreateResDTO;
import com.nice.order.center.service.dto.res.OrderDetailQueryResDTO;
import com.nice.order.center.service.service.order.OrderDetailService;
import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.web.vo.req.OrderDetailReqVO;
import com.nice.order.center.web.vo.res.OrderDetailCreateResVO;
import com.nice.order.center.web.vo.res.OrderDetailQueryResVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping(value = "/queryByUserNo/{userNo}")
    public ResponseEntity<List<OrderDetailQueryResVO>> getOrder(HttpServletRequest request,
                                                                @PathVariable("userNo") String userNo) {
        log.info("请求时间[{}]，接口URL[{}]，接口方法[{}]，调用结果[{}]，执行时间[{}]", LocalDateTime.now(), "", "", "", "");
        log.info("请求时间[{}]，接口URL[{}]，接口方法[{}]，调用结果[{}]，执行时间[{}]", LocalDateTime.now(), "", "", "", "");
        log.info("servletPath：{}", request.getServletPath());
        log.info("userId：{}", request.getParameter("userNo"));
        log.info("arbitrary：{}", request.getParameter("arbitrary"));
        List<OrderDetailQueryResDTO> resDto = orderDetailService.findOrderDetailByUserNo(userNo);
        Type type = new TypeToken<List<OrderDetailQueryResVO>>(){}.getType();
        List<OrderDetailQueryResVO> resVo = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(resDto, type);
        return ResponseEntity.status(HttpStatus.OK).body(resVo);
    }

    @GetMapping(value = "/queryByOrderNo/{orderNo}")
    public ResponseEntity<OrderDetailQueryResVO> getOrderByOrderNo(HttpServletRequest request,
                                                                   @PathVariable("orderNo") String orderNo) {
        OrderDetailQueryResDTO resDto = orderDetailService.findOrderDetailByOrderNo(orderNo);
        OrderDetailQueryResVO resVo = ModelMapperUtil.getModelMapperWithFieldMatching().map(resDto, OrderDetailQueryResVO.class);
        return ResponseEntity.status(HttpStatus.OK).body(resVo);
    }

    // TODO Get the user from session instead of parameter
    @PostMapping(value = "order/create")
    public ResponseEntity<OrderDetailCreateResVO> createOrder(@RequestBody OrderDetailReqVO orderDetailReqVO) {
        OrderDetailReqDTO orderDetailReqDto = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqVO,
                OrderDetailReqDTO.class);
        OrderDetailCreateResDTO orderDetailCreateResDto = orderDetailService.createOrder(orderDetailReqDto);
        OrderDetailCreateResVO resVo = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailCreateResDto, OrderDetailCreateResVO.class);
        return ResponseEntity.status(HttpStatus.OK).body(resVo);
    }

}
