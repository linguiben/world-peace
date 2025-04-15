package com.jupiter.mvc.pojo;

import lombok.Data;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2025/1/25
 */
@Data
public class OrderDTO {
    private String orderNo;
    private String orderName;
    private String orderDesc;
    private String orderType;
    private String orderStatus;
    private String orderAmount;
    private String orderTime;
    private String orderUser;
    private String orderAddress;
    private String orderPhone;
    private String orderEmail;
    private String orderRemark;
    private String orderExt;
}
