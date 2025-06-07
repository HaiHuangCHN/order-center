package com.nice.order.center.provider.controller;

import com.nice.order.center.common.util.JacksonUtils;
import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.provider.dto.req.OrderDetailQueryRemoteReqDTO;
import com.nice.order.center.provider.dto.res.OrderDetailQueryRemoteResDTO;
import com.nice.order.center.service.dto.res.OrderDetailQueryResDTO;
import com.nice.order.center.service.service.order.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
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

    @GetMapping(value = "/queryByOrderNo2")
    public ResponseEntity<OrderDetailQueryRemoteResDTO> getOrderByOrderNo2(@RequestParam("orderNo") String orderNo,
                                                                           @RequestBody OrderDetailQueryRemoteReqDTO orderDetailQueryReqVo) {
        log.info(JacksonUtils.objectToJsonCamel(orderDetailQueryReqVo));
        OrderDetailQueryResDTO resDto = orderDetailService.findOrderDetailByOrderNo(orderNo);
        OrderDetailQueryRemoteResDTO resVo = ModelMapperUtil.getModelMapperWithFieldMatching().map(resDto,
                OrderDetailQueryRemoteResDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(resVo);
    }

}
