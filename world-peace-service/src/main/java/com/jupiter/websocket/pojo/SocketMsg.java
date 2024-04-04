/**
 * @className SocketMsg
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-04-01 23:51
 */
package com.jupiter.websocket.pojo;

import lombok.Data;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2024-04-01 23:51
 */
@Data
public class SocketMsg {
    /**
     * 聊天类型0：群聊，1：单聊
     **/
    private int type;
    /**
     * 发送者
     **/
    private String fromUser;
    /**
     * 接受者
     **/
    private String toUser;
    /**
     * 消息
     **/
    private String msg;
}