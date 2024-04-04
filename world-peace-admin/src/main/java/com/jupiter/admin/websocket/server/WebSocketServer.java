package com.jupiter.admin.websocket.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jupiter.admin.websocket.pojo.SocketMsg;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket的具体实现类
 * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，
 * 但在springboot中连容器都是spring管理的。
 * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，
 * 所以可以用一个静态set保存起来。
 */
@Component
@ServerEndpoint(value = "/websocket/{nickname}")
public class WebSocketServer{
    /**
     * 用来存放每个客户端对应的MyWebSocket对象。
     **/
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     **/
    private Session session;
    /**
     * 用户名称
     **/
    private String nickname;
    /**
     * 用来记录sessionId和该session进行绑定
     **/
    private static Map<String,Session> map = new HashMap<String, Session>();


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("nickname") String nickname) {
        this.session = session;
        this.nickname=nickname;

        map.put(session.getId(), session);

        webSocketSet.add(this);
        System.out.println("有新连接加入:"+nickname+",当前在线人数为" + webSocketSet.size());
        this.session.getAsyncRemote().sendText("恭喜"+nickname+"成功连接上WebSocket(其频道号："+session.getId()+")-->当前在线人数为："+webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("nickname") String nickname) {
        System.out.println("来自客户端的消息-->"+nickname+": " + message);

        //从客户端传过来的数据是json数据，所以这里使用jackson进行转换为SocketMsg对象，
        // 然后通过socketMsg的type进行判断是单聊还是群聊，进行相应的处理:
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;

        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            if(socketMsg.getType() == 1){
                //单聊.需要找到发送者和接受者.
                socketMsg.setFromUser(session.getId());
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                //发送给接受者.
                if(toSession != null){
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText(nickname+"："+socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(nickname+"："+socketMsg.getMsg());
                }else{
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText("系统消息：对方不在线或者您输入的频道号不对");
                }
            }else{
                //群发消息
                broadcast(nickname+": "+socketMsg.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     */
    public void broadcast(String message) {
        for (WebSocketServer item : webSocketSet) {
            /**
             * 同步异步说明参考：
             *
             * this.session.getBasicRemote().sendText(message);
             **/
            item.session.getAsyncRemote().sendText(message);
        }
    }
}