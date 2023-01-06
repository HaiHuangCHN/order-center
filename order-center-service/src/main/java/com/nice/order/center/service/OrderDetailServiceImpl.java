package com.nice.order.center.service;

import com.nice.order.center.common.enumeration.YesOrNoEnum;
import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.dao.entity.OrderDetail;
import com.nice.order.center.dao.mapper.OrderDetailMapper;
import com.nice.order.center.dao.util.MapperUtils;
import com.nice.order.center.service.dto.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailDTO findOrderDetailByUserNo(String userNo) {
        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
                .andEqualTo(OrderDetail::getUserNo, userNo)
                .andEqualTo(OrderDetail::getYn, YesOrNoEnum.YES.getCode()));
        OrderDetail existingRecord = orderDetailMapper.selectOneByExample(example);
        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailDTO.class);
    }

    // TODO Try orderDetailMapper.insert(); see if the SQL with id column, if has id column, then pass an id that is existed in DB, see if duplicated, if id not existed in DB?
    // TODO Try selectByPrimaryKey, see if not pass primary key?
    // TODO Try existsWithPrimaryKey
    // TODO Try updateByPrimaryKey, see if not pass primary key?
    // TODO andAllEqualTo(Object param), fields with null considered as condition?

}
