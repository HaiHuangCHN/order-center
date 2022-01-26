package com.nice.order.center.web.vo.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * No yn filed
 */
@Data
public class OrderDetailReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;

    private BigDecimal totalAmount;

    private String currency;

    private Integer paymentStatus;

    private Long userId;

}
