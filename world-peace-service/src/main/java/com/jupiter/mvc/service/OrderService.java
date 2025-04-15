package com.jupiter.mvc.service;

import com.jupiter.mvc.exception.BizException;
import com.jupiter.mvc.pojo.Order;
import com.jupiter.mvc.pojo.OrderDTO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ValidationException;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */
@Slf4j
public class OrderService {
    public Order createOrder(OrderDTO dto) throws BizException {
        try {
            // 验证订单
            validateOrder(dto);

            // 处理订单
            Order order = processOrder(dto);

            return order;
        } catch (ValidationException e) {
            log.warn("订单验证失败: {}", e.getMessage());
            throw new BizException("ORDER001", "订单数据无效");
        } catch (Exception e) {
            log.error("创建订单异常", e);
            throw new BizException("ORDER002", "系统异常");
        }
    }

    private Order processOrder(OrderDTO dto) {
        return null;
    }

    private void validateOrder(OrderDTO dto) {
        if (dto == null) {
            throw new ValidationException("订单数据不能为空");
        }
        if (dto.getOrderNo() == null) {
            throw new ValidationException("订单号不能为空");
        }
        if (dto.getOrderName() == null) {
            throw new ValidationException("订单名称不能为空");
        }
        if (dto.getOrderAmount() == null) {
            throw new ValidationException("订单金额不能为空");
        }
        if (dto.getOrderTime() == null) {
            throw new ValidationException("订单时间不能为空");
        }
        if (dto.getOrderUser() == null) {
            throw new ValidationException("订单用户不能为空");
        }
        if (dto.getOrderAddress() == null) {
            throw new ValidationException("订单地址不能为空");
        }
        if (dto.getOrderPhone() == null) {
            throw new ValidationException("订单电话不能为空");
        }
        if (dto.getOrderEmail() == null) {
            throw new ValidationException("订单邮箱不能为空");
        }
    }
}