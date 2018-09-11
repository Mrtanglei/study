package com.java.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class WebsocketServer {

    private static Logger logger;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //用来存放每个客户端对应的MyWebSocket对象
//    private static Map<String,WebsocketServer> websocketServerMap;
    private static CopyOnWriteArraySet<WebsocketServer> websocketServerSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    static{
//        websocketServerMap = new Hashtable<>();
        logger = LoggerFactory.getLogger("log");
    }

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
//        websocketServerMap.put(session.getPathParameters().get("userName"),this);
        websocketServerSet.add(this);
        addOnlineCount();
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        websocketServerSet.remove(this);
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){

    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){

    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * @param message
     */
    public static void sendInfo(String message){
        websocketServerSet.forEach(websocketServer -> {
            try {
                websocketServer.sendMessage(message);
            } catch (IOException e) {

            }
        });
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void addOnlineCount(){
        WebsocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        WebsocketServer.onlineCount--;
    }
}
