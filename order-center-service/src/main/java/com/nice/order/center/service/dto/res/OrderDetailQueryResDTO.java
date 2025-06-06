package com.nice.order.center.service.dto.res;

import com.nice.order.center.dao.entity.OrderDetailStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailQueryResDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique ID for each record in the table
     */
    private Long id;

    /**
     * Order No
     */
    private String orderNo;

    /**
     * Status of an order
     */
    private OrderDetailStatusEnum orderStatus;

    /**
     * Total amount
     */
    private BigDecimal totalAmount;

    /**
     * Currency
     */
    private String currency;

    /**
     * TODO：补上去 DDL

     * Payment status. 0 - NOT_PAID, 1 - PAYING, 2 - PAID, 3 - REFUNDING, 4 - REFUNDED, -1 - PAID_FAIL, -2 - REFUND_FAIL, -3 - CLOSED
     */
    private Integer paymentStatus;

    /**
     * User No
     */
    private String userNo;

    /**
     * 0 - Deleted, 1 - Not Deleted
     */
    private Byte yn;

}
