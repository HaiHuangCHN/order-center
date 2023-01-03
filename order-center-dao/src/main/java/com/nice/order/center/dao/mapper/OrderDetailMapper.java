package com.nice.order.center.dao.mapper;

import com.nice.order.center.dao.entity.OrderDetail;
import org.springframework.stereotype.Repository;

/**
 * TODO @Repository is necessary?
 */
@Repository
public interface OrderDetailMapper /*extends Mapper<OrderDetail>*/ {

    OrderDetail selectByUserNo(String userNo);

}