package com.nice.order.center.dao.mapper;

import com.nice.order.center.dao.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//import tk.mybatis.mapper.common.Mapper;

/**
 * TODO To learn more
 */
@Mapper
// If forget to add @Mapper
//Description:
//        Field orderDetailMapper in com.nice.order.center.service.OrderDetailServiceImpl required a bean of type 'com.nice.order.center.dao.mapper.OrderDetailMapper' that could not be found.
//        The injection point has the following annotations:
//        - @org.springframework.beans.factory.annotation.Autowired(required=true)
//
//Action:
//        Consider defining a bean of type 'com.nice.order.center.dao.mapper.OrderDetailMapper' in your configuration.
public interface OrderDetailMapper/* extends Mapper<OrderDetail>*/ {

    @Select("SELECT * FROM `order_detail` WHERE `user_no` = #{userNo}")
//// MyBatis 默认「不」开启「驼峰命名映射」，需要用下面写法进行映射，或者在 application.properties 加
//// mybatis.configuration.mapUnderscoreToCamelCase=true
//// 或
//// mybatis.configuration.map-underscore-to-camel-case=true
//// https://blog.csdn.net/liu_sisi/article/details/88360155
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "totalAmount", column = "total_amount"),
//            @Result(property = "paymentStatus", column = "payment_status")
//    })
    OrderDetail selectByUserNo(@Param("userNo") String userNo);

}