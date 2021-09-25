package com.nice.order.center.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * With Lombok annotation
 */
@Table(name = "order_detail")
@Getter
@Setter
public class OrderDetail extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique ID for each record in the table
     */
    @Id
    private Long id;

    /**
     * Status of an order
     */
    @Column(name = "status", nullable = false)
    // TODO To learn more
    @Enumerated(value = EnumType.STRING)
    private OrderDetailStatusEnum status;

    /**
     * Total amount
     */
    @Column(name = "total_amount", nullable = false)
    // TODO Any problem with BigDecimal + MyBatis?
    private BigDecimal totalAmount;

    /**
     * Currency
     */
    @Column(name = "currency", nullable = false)
    private String currency;

    /**
     * Payment status, 0 - OPEN, 1 - PAYING, 2 - SUCCESS, -1 - Fail
     */
    @Column(name = "payment_status")
    private Integer paymentStatus;

    /**
     * Associated key
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 0 - Deleted, 1 - Not Deleted
     */
    @Column(name = "yn", nullable = false)
    // TODO Any problem with BOOLEAN + MyBatis?
    private Boolean yn;


}