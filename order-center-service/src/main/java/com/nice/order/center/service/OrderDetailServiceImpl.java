package com.nice.order.center.service;

import com.nice.order.center.common.constant.Constants;
import com.nice.order.center.common.enumeration.YesOrNoEnum;
import com.nice.order.center.common.exception.ErrorCode;
import com.nice.order.center.common.util.DbEffectUtils;
import com.nice.order.center.common.util.JacksonUtils;
import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.dao.entity.OrderDetail;
import com.nice.order.center.dao.mapper.OrderDetailMapper;
import com.nice.order.center.dao.util.MapperUtils;
import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;

/**
 * Try no @Autowired
 */
@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetailResDTO findOrderDetailByUserNo(String userNo) {
        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
                .andEqualTo(OrderDetail::getUserNo, userNo)
                .andEqualTo(OrderDetail::getYn, YesOrNoEnum.YES.getCode()));
        OrderDetail existingRecord = orderDetailMapper.selectOneByExample(example);
        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailResDTO.class);
    }

    // TODO Try orderDetailMapper.insert(); see if the SQL with id column, if has id column, then pass an id that is
    //  existed in DB, see if duplicated, if id not existed in DB?
    // TODO Try selectByPrimaryKey, see if not pass primary key?
    // TODO Try existsWithPrimaryKey
    // TODO Try updateByPrimaryKey, see if not pass primary key?
    // TODO andAllEqualTo(Object param), fields with null considered as condition?

    @Override
    public boolean addNewOrder(OrderDetailReqDTO orderDetailReqDto) {
        OrderDetail orderDetail = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqDto, OrderDetail.class);
        orderDetail.setOrderNo();
        orderDetail.setYn(YesOrNoEnum.YES.getCode());
        orderDetail.setCreatedBy(Constants.SYSTEM);
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setUpdatedBy(Constants.SYSTEM);
        orderDetail.setUpdatedAt(LocalDateTime.now());
        // orderDetailMapper.insert() 可以返回自增 ID
        int effectedCount = orderDetailMapper.insert(orderDetail);
        DbEffectUtils.checkEffect(effectedCount != 1, ErrorCode.GENERAL_BUSINESS_ERROR.GENERAL_BUSINESS_ERROR_CODE);
        return true;
    }

}
