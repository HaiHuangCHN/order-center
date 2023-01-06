package com.nice.order.center.web.vo.res;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * No yn filed
 */
@Getter
@Setter
public class OrderDetailResVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderNo;

    private String orderStatus;

    private BigDecimal totalAmount;

    private String currency;

    private Integer paymentStatus;

    private String userNo;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

}
