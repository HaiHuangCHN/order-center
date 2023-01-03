package com.nice.order.center.service.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailResDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderNo;

    private String orderStatus;

    private BigDecimal totalAmount;

    private String currency;

    private Integer paymentStatus;

    private String userId;

    private Boolean yn;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

}
