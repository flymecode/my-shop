package com.xupt.admin.mapper.provider;

import com.xupt.domain.order.OrderForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author maxu
 * @date 2019/6/12
 */
public class OrderProvicer {

    public String serachOrder(final OrderForm order) {
        return new SQL() {{
            SELECT("order_id as id,payment,status,payment_time,consign_time,end_time,close_time,user_id");
            FROM("tb_order");
            if (StringUtils.isNotEmpty(order.getId())) {
                WHERE("order_id = " + order.getId());
            }
            if (order.getStatus() != null) {
                WHERE("status = " + order.getStatus());
            }
            if (order.getUserId() != null) {
                WHERE("user_id = " + order.getUserId());
            }
        }}.toString();
    }
}
