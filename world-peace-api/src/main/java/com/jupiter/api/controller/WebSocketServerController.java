/**
 * @className WebSocketController
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2024-02-22 21:32
 */
package com.jupiter.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *@desc 原生websocket实现
 *@author Jupiter.Lin
 *@date 2024-02-22 21:32
 */
@Slf4j
@ServerEndpoint("/api/websocket/{id}/{name}") // 原生websocket,java提供的注解,监听一个WebSocket请求路径
@Component
public class WebSocketServerController {
    // 用来记录当前连接数的变量
    private static volatile int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServerController> webSocketSet = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来与客户端进行数据收发
    private Session session;
    private Long id; // 首次建立连接时赋值
    private String name;
    
    @OnOpen
    public void onOpen(Session session, @PathParam("id") long id, @PathParam("name") String name) throws Exception {
        this.session = session;
        this.id = id;
        this.name = name;
        System.out.println(this.session.getId());
        webSocketSet.add(this);
        log.info("Open a websocket. userId={}, name={}", id, name);
        sendMessage("hello， this is websocket server!");
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("Close a websocket. ");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("Receive a message from client:{}",message);
        sendMessage("Received your message:"+message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Error while websocket. ", error);
    }

    public void sendMessage(String message) throws Exception {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServerController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServerController.onlineCount--;
    }
}

