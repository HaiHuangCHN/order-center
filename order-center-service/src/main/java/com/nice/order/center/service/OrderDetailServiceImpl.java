package com.nice.order.center.service;

import com.nice.order.center.common.constant.Constants;
import com.nice.order.center.common.enumeration.YesOrNoEnum;
import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.dao.entity.OrderDetail;
import com.nice.order.center.dao.mapper.OrderDetailMapper;
import com.nice.order.center.dao.util.MapperUtils;
import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
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
    public OrderDetailResDTO findOrderDetailByUserId(Long userId) {
        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
                .andEqualTo(OrderDetail::getUserId, userId)
                .andEqualTo(OrderDetail::getYn, YesOrNoEnum.YES.getCode()));
        OrderDetail orderDetail = orderDetailMapper.selectOneByExample(example);
        return ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetail, OrderDetailResDTO.class);
    }

    @Override
    public String addNewOrder(OrderDetailReqDTO orderDetailReqDTO) {
        OrderDetail orderDetail = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqDTO, OrderDetail.class);
        orderDetail.setCreatedBy(Constants.SYSTEM);
        boolean updateN = orderDetailMapper.insert(orderDetail) == 1;
        if (!updateN) {
            // TODO
        }
        // TODO
        return "A";
    }


}
