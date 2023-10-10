package com.nice.order.center.service.service.order;

import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailQueryResDTO;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailQueryResDTO> findOrderDetailByUserNo(String userNo);

    OrderDetailQueryResDTO findOrderDetailByOrderNo(String orderNo);

    boolean addNewOrder(OrderDetailReqDTO orderDetailReqDTO);

}
