package com.jupiter.mvc.dao;

import com.jupiter.mvc.pojo.Order;
import org.apache.ibatis.annotations.*;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */
@Mapper
public interface OrdreMapper {

    @Insert("insert into order(orderNo, orderName, orderDesc, orderType, orderStatus, orderAmount, orderTime, orderUser, orderAddress, orderPhone, orderEmail, orderRemark, orderExt) values(#{orderNo}, #{orderName}, #{orderDesc}, #{orderType}, #{orderStatus}, #{orderAmount}, #{orderTime}, #{orderUser}, #{orderAddress}, #{orderPhone}, #{orderEmail}, #{orderRemark}, #{orderExt})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId", keyColumn = "orderId")
    void insertOrder(Order order);

    @Delete("delete from order where orderNo = #{orderNo}")
    void deleteOrder(Order order);

    @Update("update order set orderStatus = #{orderStatus} where orderNo = #{orderNo}")
    void updateOrder(Order order);

    @Select("select * from order where orderNo = #{orderNo}")
    Order selectOrder(String orderNo);
}
