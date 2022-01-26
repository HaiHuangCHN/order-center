package com.nice.order.center.dao.mapper;

import com.nice.order.center.dao.entity.OrderDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * TODO To learn more
 */
@Repository
public interface OrderDetailMapper /*extends Mapper<OrderDetail>*/ {

    OrderDetail selectByUserId(Long id);

}