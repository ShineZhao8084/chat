package com.dj.chat.main.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * base_friend
 * @author 
 */
public class BaseFriend implements Serializable {

    private Long friendId;

    private Long friendAccountId;

    private Long friendMyselfId;

    private String friendName;

    private static final long serialVersionUID = 1L;

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getFriendAccountId() {
        return friendAccountId;
    }

    public void setFriendAccountId(Long friendAccountId) {
        this.friendAccountId = friendAccountId;
    }

    public Long getFriendMyselfId() {
        return friendMyselfId;
    }

    public void setFriendMyselfId(Long friendMyselfId) {
        this.friendMyselfId = friendMyselfId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
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
        BaseFriend other = (BaseFriend) that;
        return (this.getFriendId() == null ? other.getFriendId() == null : this.getFriendId().equals(other.getFriendId()))
            && (this.getFriendAccountId() == null ? other.getFriendAccountId() == null : this.getFriendAccountId().equals(other.getFriendAccountId()))
            && (this.getFriendMyselfId() == null ? other.getFriendMyselfId() == null : this.getFriendMyselfId().equals(other.getFriendMyselfId()))
            && (this.getFriendName() == null ? other.getFriendName() == null : this.getFriendName().equals(other.getFriendName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFriendId() == null) ? 0 : getFriendId().hashCode());
        result = prime * result + ((getFriendAccountId() == null) ? 0 : getFriendAccountId().hashCode());
        result = prime * result + ((getFriendMyselfId() == null) ? 0 : getFriendMyselfId().hashCode());
        result = prime * result + ((getFriendName() == null) ? 0 : getFriendName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", friendId=").append(friendId);
        sb.append(", friendAccountId=").append(friendAccountId);
        sb.append(", friendMyselfId=").append(friendMyselfId);
        sb.append(", friendName=").append(friendName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}