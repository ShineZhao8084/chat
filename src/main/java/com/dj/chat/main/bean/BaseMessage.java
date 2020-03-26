package com.dj.chat.main.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
public class BaseMessage implements Serializable {
    private Long baseMessageId;

    private Long baseMessageBelong;

    private String baseMessageContent;

    /**
     * 0:未读
     */
    private Integer baseMessageStatus;

    private Integer baseMessageTypeId;

    private Date baseMessageSendTime;

    private Long baseMessageFrom;

    private Long baseMessageTo;

    private static final long serialVersionUID = 1L;

    public Long getBaseMessageId() {
        return baseMessageId;
    }

    public void setBaseMessageId(Long baseMessageId) {
        this.baseMessageId = baseMessageId;
    }

    public Long getBaseMessageBelong() {
        return baseMessageBelong;
    }

    public void setBaseMessageBelong(Long baseMessageBelong) {
        this.baseMessageBelong = baseMessageBelong;
    }

    public String getBaseMessageContent() {
        return baseMessageContent;
    }

    public void setBaseMessageContent(String baseMessageContent) {
        this.baseMessageContent = baseMessageContent;
    }

    public Integer getBaseMessageStatus() {
        return baseMessageStatus;
    }

    public void setBaseMessageStatus(Integer baseMessageStatus) {
        this.baseMessageStatus = baseMessageStatus;
    }

    public Integer getBaseMessageTypeId() {
        return baseMessageTypeId;
    }

    public void setBaseMessageTypeId(Integer baseMessageTypeId) {
        this.baseMessageTypeId = baseMessageTypeId;
    }

    public Date getBaseMessageSendTime() {
        return baseMessageSendTime;
    }

    public void setBaseMessageSendTime(Date baseMessageSendTime) {
        this.baseMessageSendTime = baseMessageSendTime;
    }

    public Long getBaseMessageFrom() {
        return baseMessageFrom;
    }

    public void setBaseMessageFrom(Long baseMessageFrom) {
        this.baseMessageFrom = baseMessageFrom;
    }

    public Long getBaseMessageTo() {
        return baseMessageTo;
    }

    public void setBaseMessageTo(Long baseMessageTo) {
        this.baseMessageTo = baseMessageTo;
    }
}