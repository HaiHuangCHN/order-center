package com.nice.order.center.service.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailCreateResDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderNo;

}
