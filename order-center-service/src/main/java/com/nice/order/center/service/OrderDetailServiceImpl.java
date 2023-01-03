package com.nice.order.center.service;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.dao.entity.OrderDetail;
import com.nice.order.center.dao.mapper.OrderDetailMapper;
import com.nice.order.center.service.dto.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tk.mybatis.mapper.entity.Example;

/**
 * Try no @Autowired
 */
@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailDTO findOrderDetailByUserNo(String userNo) {
//        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
//                .andEqualTo(OrderDetail::getUserNo, userNo)
//                .andEqualTo(OrderDetail::getYn, (byte)1));
//        OrderDetail existingRecord = orderDetailMapper.selectOneByExample(example);
//        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailDTO.class);
        OrderDetail existingRecord = orderDetailMapper.selectByUserNo(userNo);
        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailDTO.class);
    }

}
