package com.nice.order.center.dao.entity.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Document(collection = "test")
@Getter
@Setter
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Order No
     */
    @Id
    private String orderNo;

    /**
     * User No
     */
    private String userNo;

    /**
     * Total amount
     */
    private BigDecimal totalAmount;

}