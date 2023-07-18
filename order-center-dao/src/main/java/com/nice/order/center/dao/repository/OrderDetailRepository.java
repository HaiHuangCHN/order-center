package com.nice.order.center.dao.repository;

import com.nice.order.center.dao.entity.mongo.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 第一种：DAO层继承 MongoRepository
 *
 * @author haihuang95@zto.com
 * @date 2023/7/18 14:02
 */
public interface OrderDetailRepository extends MongoRepository<OrderDetail, Long> {

    /**
     * 根据 userNo 查找 order
     *
     * @param userNo
     * @return
     */
    OrderDetail findByUserNo(String userNo);

}
