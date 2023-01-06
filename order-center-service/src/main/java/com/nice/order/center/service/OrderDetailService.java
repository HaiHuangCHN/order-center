package com.nice.order.center.service;

import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;

public interface OrderDetailService {

    OrderDetailResDTO findOrderDetailByUserNo(String userNo);

    String addNewOrder(OrderDetailReqDTO orderDetailReqDTO);

}
