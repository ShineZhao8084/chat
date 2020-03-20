package com.dj.chat.main.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * base_message
 * @author 
 */
public class BaseMessage implements Serializable {
    private Long baseMessageId;

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseMessage other = (BaseMessage) that;
        return (this.getBaseMessageId() == null ? other.getBaseMessageId() == null : this.getBaseMessageId().equals(other.getBaseMessageId()))
            && (this.getBaseMessageContent() == null ? other.getBaseMessageContent() == null : this.getBaseMessageContent().equals(other.getBaseMessageContent()))
            && (this.getBaseMessageStatus() == null ? other.getBaseMessageStatus() == null : this.getBaseMessageStatus().equals(other.getBaseMessageStatus()))
            && (this.getBaseMessageTypeId() == null ? other.getBaseMessageTypeId() == null : this.getBaseMessageTypeId().equals(other.getBaseMessageTypeId()))
            && (this.getBaseMessageSendTime() == null ? other.getBaseMessageSendTime() == null : this.getBaseMessageSendTime().equals(other.getBaseMessageSendTime()))
            && (this.getBaseMessageFrom() == null ? other.getBaseMessageFrom() == null : this.getBaseMessageFrom().equals(other.getBaseMessageFrom()))
            && (this.getBaseMessageTo() == null ? other.getBaseMessageTo() == null : this.getBaseMessageTo().equals(other.getBaseMessageTo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBaseMessageId() == null) ? 0 : getBaseMessageId().hashCode());
        result = prime * result + ((getBaseMessageContent() == null) ? 0 : getBaseMessageContent().hashCode());
        result = prime * result + ((getBaseMessageStatus() == null) ? 0 : getBaseMessageStatus().hashCode());
        result = prime * result + ((getBaseMessageTypeId() == null) ? 0 : getBaseMessageTypeId().hashCode());
        result = prime * result + ((getBaseMessageSendTime() == null) ? 0 : getBaseMessageSendTime().hashCode());
        result = prime * result + ((getBaseMessageFrom() == null) ? 0 : getBaseMessageFrom().hashCode());
        result = prime * result + ((getBaseMessageTo() == null) ? 0 : getBaseMessageTo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", baseMessageId=").append(baseMessageId);
        sb.append(", baseMessageContent=").append(baseMessageContent);
        sb.append(", baseMessageStatus=").append(baseMessageStatus);
        sb.append(", baseMessageTypeId=").append(baseMessageTypeId);
        sb.append(", baseMessageSendTime=").append(baseMessageSendTime);
        sb.append(", baseMessageFrom=").append(baseMessageFrom);
        sb.append(", baseMessageTo=").append(baseMessageTo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}