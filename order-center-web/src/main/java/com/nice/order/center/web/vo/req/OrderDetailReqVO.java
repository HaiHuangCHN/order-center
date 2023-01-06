package com.nice.order.center.web.vo.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderDetailReqVO implements Serializable {

    private static final long serialVersionUID = 8638145783117356900L;

    private BigDecimal totalAmount;

    private String currency;

    private String userNo;

}
