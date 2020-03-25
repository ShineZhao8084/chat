package com.dj.chat.core.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dj.chat.main.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imserver/{accountId}")
@Component
public class WebSocketServer {

    private static Log log = LogFactory.getLog(WebSocketServer.class);
    private static int onlineCount = 0; //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;  //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private String accountId;  //accountId
    @Resource
    private MessageService messageService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("accountId") String accountId) {
        this.session = session;
        this.accountId = accountId;
        if (webSocketMap.containsKey(accountId)) {
            webSocketMap.remove(accountId);
        } else {
            addOnlineCount();
        }
        webSocketMap.put(accountId, this);
        log.info("用户连接:" + accountId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(accountId)) {
            webSocketMap.remove(accountId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + accountId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + accountId + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromAccountId", this.accountId);
                String toAccountId = jsonObject.getString("toAccountId");
                //传送给对应toAccountId用户的websocket

                if (StringUtils.isNotBlank(toAccountId) && webSocketMap.containsKey(toAccountId)) {
                    webSocketMap.get(toAccountId).sendMessage(jsonObject.toJSONString());

                } else {
                    log.error("请求的accountId:" + toAccountId + "不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.accountId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("accountId") String accountId) throws IOException {
        log.info("发送消息到:" + accountId + "，报文:" + message);
        if (StringUtils.isNotBlank(accountId) && webSocketMap.containsKey(accountId)) {
            webSocketMap.get(accountId).sendMessage(message);
        } else {
            log.error("用户" + accountId + ", 不在线！");
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
