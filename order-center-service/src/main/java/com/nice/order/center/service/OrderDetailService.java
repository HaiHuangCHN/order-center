package com.nice.order.center.service;

import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;

public interface OrderDetailService {


    OrderDetailResDTO findOrderDetailByUserId(Long userId);

    Boolean addNewOrder(OrderDetailReqDTO orderDetailReqDTO);


}
