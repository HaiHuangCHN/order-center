package com.nice.order.center.service.service.order;

import com.nice.order.center.service.dto.res.OrderDetailResDTO;

public interface OrderDetailMongoService {

    OrderDetailResDTO findOrderDetailByUserNo(String userNo);

}
