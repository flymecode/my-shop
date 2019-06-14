package com.xupt.domain.order;

import lombok.Data;

import java.util.Date;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Data
public class BrokerMessageLog {

    private String messageId;
    private Integer tryCount = 0;
    private String status;
    // 下一次重试的时间
    private Date nextRetry;
    private String message;
    private Date created;
    private Date updated;

}
