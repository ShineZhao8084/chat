package com.dj.chat.core.websocket.bean;

/**
 * 服务端接收发送消息消息体
 * 识别码: 800
 * 服务端识别消息类型是800则进行消息入库操作
 */
public class MessageReceive {

    private Long fromAccountId;
    private Long toAccountId;
    private String contentType;  //消息类型 text；image
    private String contentText;

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
