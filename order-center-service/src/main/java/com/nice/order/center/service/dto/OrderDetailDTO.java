package com.nice.order.center.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDetailDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;

    private String status;

    private BigDecimal totalAmount;

    private String currency;

    private Integer paymentStatus;

    private Long userId;

    private Boolean yn;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;


}
