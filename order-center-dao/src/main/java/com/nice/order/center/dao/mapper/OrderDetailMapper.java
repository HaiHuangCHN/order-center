package com.nice.order.center.dao.mapper;


import com.nice.order.center.dao.entity.OrderDetail;
import org.apache.ibatis.annotations.*;
//import tk.mybatis.mapper.common.Mapper;

/**
 * TODO To learn more
 */
@Mapper
public interface OrderDetailMapper/* extends Mapper<OrderDetail>*/ {

    @Select("SELECT * FROM order_detail WHERE id = #{id}")
//// MyBatis 默认「不」开启「驼峰命名映射」，需要用下面写法进行映射，或者在 application.properties 加
//// mybatis.configuration.mapUnderscoreToCamelCase=true
//// 或
//// mybatis.configuration.map-underscore-to-camel-case=true
//// https://blog.csdn.net/liu_sisi/article/details/88360155
//    @Results(value = {
//            @Result(property = "id", column = "ID"),
//            @Result(property = "totalAmount", column = "total_amount"),
//            @Result(property = "paymentStatus", column = "payment_status")
//    })
    OrderDetail getArticle(@Param("id") Long id);

}