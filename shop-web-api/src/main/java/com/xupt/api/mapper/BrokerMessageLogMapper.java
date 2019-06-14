package com.xupt.api.mapper;

import com.xupt.domain.order.BrokerMessageLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/10
 */
@Mapper
public interface BrokerMessageLogMapper {
    @Update("update tb_message_log b set b.status = #{status,jdbcType=VARCHAR},b.update_time = #{date,jdbcType=TIMESTAMP} where b.message_id = #{messageId,jdbcType=VARCHAR}")
    void changeBrokerMessageLogStats(@Param("messageId") String id, @Param("status") String status, @Param("date") Date date);

    @Update("update tb_message_log set try_count = try_count + 1,update_time = #{date} where message_id = #{id}")
    void updateCount(@Param("id") String id,@Param("date") Date date);

    @Select("select message_id, message, try_count from tb_message_log where status = '0' and next_retry < sysdate() ")
    List<BrokerMessageLog> getStatusAndTimeoutMessage();

    @Insert("insert into tb_message_log (message_id,message,try_count,status,next_retry,create_time,update_time) value (#{messageId},#{message},#{tryCount},#{status},#{nextRetry},#{created},#{updated})")
    void insert(BrokerMessageLog brokerMessageLog);
}
