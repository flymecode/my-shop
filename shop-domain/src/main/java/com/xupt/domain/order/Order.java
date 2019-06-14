package com.xupt.domain.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Data
public class Order implements Serializable {

    private String id;
    private Double payment;
    private String paymentType;
    private Integer status;
    private Date paymentTime;
    private Date consignTime;
    private Date endTime;
    private Date closeTime;
    private Long userId;
    private String shippingName;
    private String shippingCode;
    private String buyerMessage;
    private String buyerRate;
    private String messageId;
    private Date created;
    private Date updated;
}
