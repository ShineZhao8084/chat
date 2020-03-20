package com.dj.chat.main.bean;

import java.io.Serializable;

/**
 * base_message_type
 * @author 
 */
public class BaseMessageType implements Serializable {
    private Integer messsgeTypeId;

    private String messsgeTypeName;

    private static final long serialVersionUID = 1L;

    public Integer getMesssgeTypeId() {
        return messsgeTypeId;
    }

    public void setMesssgeTypeId(Integer messsgeTypeId) {
        this.messsgeTypeId = messsgeTypeId;
    }

    public String getMesssgeTypeName() {
        return messsgeTypeName;
    }

    public void setMesssgeTypeName(String messsgeTypeName) {
        this.messsgeTypeName = messsgeTypeName;
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
        BaseMessageType other = (BaseMessageType) that;
        return (this.getMesssgeTypeId() == null ? other.getMesssgeTypeId() == null : this.getMesssgeTypeId().equals(other.getMesssgeTypeId()))
            && (this.getMesssgeTypeName() == null ? other.getMesssgeTypeName() == null : this.getMesssgeTypeName().equals(other.getMesssgeTypeName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMesssgeTypeId() == null) ? 0 : getMesssgeTypeId().hashCode());
        result = prime * result + ((getMesssgeTypeName() == null) ? 0 : getMesssgeTypeName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messsgeTypeId=").append(messsgeTypeId);
        sb.append(", messsgeTypeName=").append(messsgeTypeName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}