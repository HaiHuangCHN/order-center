package com.nice.order.center.service.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDetailReqDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    private BigDecimal totalAmount;

    private String currency;

    private Long userId;

    private String createdBy;

    private String updatedBy;


}
