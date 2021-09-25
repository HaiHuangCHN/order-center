package com.nice.order.center.service;

import com.nice.order.center.service.dto.OrderDetailDTO;

public interface OrderDetailService {

    OrderDetailDTO findOrderDetailByUserId(Long userId);

}
