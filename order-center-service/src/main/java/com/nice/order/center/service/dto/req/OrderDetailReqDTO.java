package com.nice.order.center.service.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderDetailReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal totalAmount;

    private String currency;

    private String userNo;

    private String createdBy;

    private String updatedBy;

}
