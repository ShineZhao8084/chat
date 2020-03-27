package com.dj.chat.core.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dj.chat.core.websocket.bean.MessageReceive;
import com.dj.chat.core.websocket.bean.MessageReceiveResponse;
import com.dj.chat.core.websocket.bean.MessageSend;
import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseMessage;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/imserver/{accountId}")
@Component
public class WebSocketServer {

    private static Log log = LogFactory.getLog(WebSocketServer.class);
    private static int onlineCount = 0; //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;  //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Long accountId;  //accountId
    @Resource
    private MessageService messageService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("accountId") Long accountId) {
        this.session = session;
        this.accountId = accountId;
        if (webSocketMap.containsKey(accountId.toString())) {
            webSocketMap.remove(accountId.toString());
        } else {
            addOnlineCount();
        }
        webSocketMap.put(accountId.toString(), this);
        log.info("用户连接:" + accountId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(accountId.toString())) {
            webSocketMap.remove(accountId.toString());
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
                String messageType = jsonObject.getString("messageType");
                JSONObject innerObject = jsonObject.getJSONObject("object");
                if ("800".equals(messageType)) {  //服务端接收发送消息消息体
                    MessageReceive messageReceive = (MessageReceive) JSONObject.toJavaObject(innerObject, MessageReceive.class);
                    String toAccountId = messageReceive.getToAccountId().toString();
                    //追加发送人(防止篡改)
                    messageReceive.setFromAccountId(this.accountId);
                    String fromAccountId = messageReceive.getFromAccountId().toString();

                    // 消息入库，并更新发送人会话、接收人会话，查询发送人会话列表接收人会话列表返回此处
                    Map<String, List<BaseDialogue>> baseDialogueListMap = messageService.onMessage(messageReceive);

                    // 发送发送消息返回回执给发送人；内容是发送人会话列表
                    Map<String, Object> messageReceiveResponseMap = new HashMap<>();
                    messageReceiveResponseMap.put("messageType", "801");
                    MessageReceiveResponse messageReceiveResponse = new MessageReceiveResponse();
                    //todo
                    messageReceiveResponseMap.put("object", messageReceiveResponse);
                    webSocketMap.get(fromAccountId).sendMessage(JSON.toJSONString(messageReceiveResponseMap));

                    // 如果接收人在线，发送服务端发送消息消息体，内容包括消息信息和会话列表
                    if (StringUtils.isNotBlank(toAccountId) && webSocketMap.containsKey(toAccountId)) {
                        Map<String, Object> messageSendMap = new HashMap<>();
                        messageSendMap.put("messageType", "801");
                        MessageSend messageSend = new MessageSend();
                        //todo
                        messageSendMap.put("object", messageSend);
                        webSocketMap.get(toAccountId).sendMessage(JSON.toJSONString(messageSendMap));
                    }

                } else if ("803".equals(messageType)) {

                } else {
                    Map<String, String> returnMap = new HashMap<>();
                    returnMap.put("messageType", "500");
                    returnMap.put("messageContent", "不支持的消息类型");
                    webSocketMap.get(this.accountId.toString()).sendMessage(JSON.toJSONString(returnMap));
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
        log.error("用户错误:" + this.accountId + ", 原因:" + error.getMessage());
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
    public static void sendInfo(String message, @PathParam("accountId") Long accountId) throws IOException {
        log.info("发送消息到:" + accountId + "，报文:" + message);
        if (StringUtils.isNotBlank(accountId.toString()) && webSocketMap.containsKey(accountId.toString())) {
            webSocketMap.get(accountId.toString()).sendMessage(message);
        } else {
            log.error("用户" + accountId + ", 不在线！");
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount ++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount --;
    }

}
