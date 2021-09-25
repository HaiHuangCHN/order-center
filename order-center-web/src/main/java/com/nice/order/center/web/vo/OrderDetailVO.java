package com.nice.order.center.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * No yn filed
 */
@Data
public class OrderDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String status;

    private BigDecimal totalAmount;

    private String currency;

    private Integer paymentStatus;

    private Long userId;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

}
