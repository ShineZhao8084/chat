package com.dj.chat.main.bean;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class BaseDialogue implements Serializable {
    private Long dialogueId;

    private Long dialogueFriendId;

    private Long dialogueMyselfId;

    private Long lastMessageId;

    private String lastReadMessageContent;

    private Long lastReadMessageId;

    private Integer unreadMessageCount;

    private static final long serialVersionUID = 1L;

    public Long getDialogueId() {
        return dialogueId;
    }

    public void setDialogueId(Long dialogueId) {
        this.dialogueId = dialogueId;
    }

    public Long getDialogueFriendId() {
        return dialogueFriendId;
    }

    public void setDialogueFriendId(Long dialogueFriendId) {
        this.dialogueFriendId = dialogueFriendId;
    }

    public Long getDialogueMyselfId() {
        return dialogueMyselfId;
    }

    public void setDialogueMyselfId(Long dialogueMyselfId) {
        this.dialogueMyselfId = dialogueMyselfId;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public String getLastReadMessageContent() {
        return lastReadMessageContent;
    }

    public void setLastReadMessageContent(String lastReadMessageContent) {
        this.lastReadMessageContent = lastReadMessageContent;
    }

    public Long getLastReadMessageId() {
        return lastReadMessageId;
    }

    public void setLastReadMessageId(Long lastReadMessageId) {
        this.lastReadMessageId = lastReadMessageId;
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(Integer unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }
}