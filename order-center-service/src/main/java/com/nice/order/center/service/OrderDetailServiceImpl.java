package com.nice.order.center.service;

import com.nice.order.center.common.enumeration.YesOrNoEnum;
import com.nice.order.center.common.exception.ErrorCode;
import com.nice.order.center.common.util.DbEffectUtils;
import com.nice.order.center.common.util.ModelMapperUtil;
import com.nice.order.center.common.util.SnowFlakeShortUrlUtil;
import com.nice.order.center.dao.entity.OrderDetail;
import com.nice.order.center.dao.mapper.OrderDetailMapper;
import com.nice.order.center.dao.util.MapperUtils;
import com.nice.order.center.service.dto.req.OrderDetailReqDTO;
import com.nice.order.center.service.dto.res.OrderDetailResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;

/**
 * Try no @Autowired
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    /**
     * TODO How to make it as global variable?
     */
    private static final SnowFlakeShortUrlUtil SNOW_FLAKE = new SnowFlakeShortUrlUtil(10, 4);

    private final OrderDetailMapper orderDetailMapper;

    /**
     * selectByPrimaryKey, if not pass primary key, will be something like this:
     * ==>  Preparing: SELECT created_by,created_at,updated_by,updated_at,id,order_no,order_status,total_amount,
     * currency,payment_status,user_no,yn FROM order_detail WHERE id = ?
     * ==> Parameters: null
     * <==      Total: 0
     * <p>
     * existsWithPrimaryKey
     * ==> Parameters: null
     * <==      Total: 1
     * <p>
     * andAllEqualTo(Object param), fields with null considered as condition? Yes!
     * Example example = new Example(OrderDetail.class);
     * Example.Criteria criteria = example.createCriteria();
     * OrderDetail condition = new OrderDetail();
     * condition.setId(1L);
     * criteria.andAllEqualTo(condition);
     * log.info(JacksonUtils.objectToJsonCamel(orderDetailMapper.selectByExample(example)));
     * return ModelMapperUtil.getModelMapperWithFieldMatching().map(null, OrderDetailResDTO.class);
     * <p>
     * ==>  Preparing: SELECT created_by,created_at,updated_by,updated_at,id,order_no,order_status,total_amount,
     * currency,payment_status,user_no,yn FROM order_detail WHERE ( ( total_amount is null and created_at is null and
     * updated_by is null and order_no is null and created_by is null and yn is null and user_no is null and
     * order_status is null and currency is null and id = ? and payment_status is null and updated_at is null ) )
     * ==> Parameters: 1(Long)
     * <==      Total: 0
     *
     * @param userNo
     * @return
     */
    @Override
    public OrderDetailResDTO findOrderDetailByUserNo(String userNo) {
        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
                .andEqualTo(OrderDetail::getUserNo, userNo)
                .andEqualTo(OrderDetail::getYn, YesOrNoEnum.YES.getCode()));
        OrderDetail existingRecord = orderDetailMapper.selectOneByExample(example);
        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailResDTO.class);
    }

    /**
     * @Cacheable(cacheNames = "orders", key = "'userNo'")
     * 'userNo' = this is to use a consistent key for the cache
     *
     * @Cacheable(cacheNames = "orders", key = "#userNo")
     * #userNo = this is to use SpEL Expression to specify the key
     *
     * @param userNo
     * @return
     */
    @Override
    @Cacheable(cacheNames = "orders", key = "#userNo")
    public OrderDetailResDTO findOrderDetailByUserNoWithCache(String userNo) {
        Example example = MapperUtils.buildExample(OrderDetail.class, o -> o
                .andEqualTo(OrderDetail::getUserNo, userNo)
                .andEqualTo(OrderDetail::getYn, YesOrNoEnum.YES.getCode()));
        OrderDetail existingRecord = orderDetailMapper.selectOneByExample(example);
        return ModelMapperUtil.getModelMapperWithFieldMatching().map(existingRecord, OrderDetailResDTO.class);
    }

    @Override
    public boolean addNewOrder(OrderDetailReqDTO orderDetailReqDto) {
        OrderDetail orderDetail = ModelMapperUtil.DEFAULT_MODEL_MAPPER.map(orderDetailReqDto, OrderDetail.class);
        // If pass an id that is existed in DB, will get java.sql.SQLIntegrityConstraintViolationException: Duplicate
        // entry 'id' for key 'order_detail.PRIMARY'
        // if id not existed in DB, then successfully
        // orderDetail.setId(5L);
        orderDetail.setOrderNo(String.valueOf(SNOW_FLAKE.nextId()));
        orderDetail.setPaymentStatus(0);
        orderDetail.setYn(YesOrNoEnum.YES.getCode());
        orderDetail.setCreatedBy(orderDetailReqDto.getUserNo());
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setUpdatedBy(orderDetailReqDto.getUserNo());
        orderDetail.setUpdatedAt(LocalDateTime.now());
        // orderDetailMapper.insert() 可以返回自增 ID
        int effectedCount = orderDetailMapper.insertSelective(orderDetail);
        DbEffectUtils.checkEffect(effectedCount == 1, ErrorCode.GENERAL_BUSINESS_ERROR.GENERAL_BUSINESS_ERROR_CODE);
        return true;
    }

}
