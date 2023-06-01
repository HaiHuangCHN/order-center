package com.nice.order.center.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {

    NOT_PAID(0, "未支付", null),

    PAYING(1, "支付中", 0),

    PAID(2, "支付成功", 1),

    REFUNDING(3, "退款中", 2),

    REFUNDED(4, "已退款", 3),

    PAY_FAIL(-1, "支付失败", 4),

    REFUND_FAIL(-2, "退款失败", 5),

    CLOSED(-3, "支付关闭", 6);

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 备注
     */
    private final String remark;

    /**
     * 与支付中台支付结果的对应关系
     */
    private final Integer payCenterPaymentStatusMapping;

    /**
     * 本系统支付状态与支付中台支付状态的映射
     */
    public static final Map<Integer, Integer> PAY_STATUS_MAPPING =
            Stream.of(PaymentStatusEnum.values()).collect(Collectors.toMap(PaymentStatusEnum::getPayCenterPaymentStatusMapping, PaymentStatusEnum::getCode));

}
