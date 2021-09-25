package com.nice.order.center.service;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.dao.entity.OrderDetail;
import com.nice.order.center.dao.mapper.OrderDetailMapper;
import com.nice.order.center.dao.util.MapperUtils;
import com.nice.order.center.service.dto.OrderDetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * Try no @Autowired
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailDTO findOrderDetailByUserId(Long userId) {
        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
                .andEqualTo(OrderDetail::getUserId, userId)
                .andEqualTo(OrderDetail::getYn, (byte)1));
        OrderDetail existingRecord = orderDetailMapper.selectOneByExample(example);
        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailDTO.class);
    }


}
