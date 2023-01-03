package com.nice.order.center.service;

import com.nice.order.center.service.dto.res.OrderDetailResDTO;

public interface OrderDetailService {


    OrderDetailResDTO queryOrderDetailByUserNo(String userNo);

//    String addNewOrder(OrderDetailReqDTO orderDetailReqDTO);


}
