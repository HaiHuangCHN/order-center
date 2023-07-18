package com.nice.order.center.service.service.order.impl;

import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.dao.entity.mongo.OrderDetail;
import com.nice.order.center.dao.repository.OrderDetailRepository;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import com.nice.order.center.service.service.order.OrderDetailMongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDetailMongoServiceImpl implements OrderDetailMongoService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailResDTO findOrderDetailByUserNo(String userNo) {
        OrderDetail existingRecord = orderDetailRepository.findByUserNo(userNo);
        if (existingRecord == null) {
            return null;
        }

        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailResDTO.class);
    }

}
