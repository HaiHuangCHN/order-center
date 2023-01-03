package com.nice.order.center.dao.mapper;

import com.nice.order.center.dao.entity.OrderDetail;

public interface OrderDetailMapper {

    OrderDetail selectByUserNo(String userNo);

}