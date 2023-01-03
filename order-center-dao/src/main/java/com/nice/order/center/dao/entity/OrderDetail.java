package com.nice.order.center.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Order No
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * Status of an order
     */
    @Column(name = "order_status")
    // TODO To learn more
    @Enumerated(value = EnumType.STRING)
    private OrderDetailStatusEnum orderStatus;

    /**
     * Total amount
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * Currency
     */
    @Column(name = "currency")
    private String currency;

    /**
     * Payment status. 0 - NOT_PAID, 1 - PAYING, 2 - PAID, 3 - REFUNDING, 4 - REFUNDED, -1 - PAID_Fail
     */
    @Column(name = "payment_status")
    private Integer paymentStatus;

    /**
     * User No
     */
    @Column(name = "user_no")
    private String userNo;

    /**
     * 0 - Deleted, 1 - Not Deleted
     */
    @Column(name = "yn", nullable = false)
    private Byte yn;

}