package com.nice.order.center.service.service;

import com.nice.order.center.service.dto.res.OrderDetailResDTO;

public interface OrderDetailMongoService {

    OrderDetailResDTO findOrderDetailByUserNo(String userNo);

}
