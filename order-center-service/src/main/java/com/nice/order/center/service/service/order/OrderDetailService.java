package com.nice.order.center.service.service.order;

import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;

public interface OrderDetailService {

    OrderDetailResDTO findOrderDetailByUserNo(String userNo);

    boolean addNewOrder(OrderDetailReqDTO orderDetailReqDTO);

}
