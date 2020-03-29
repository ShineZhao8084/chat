package com.dj.chat.main.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
public class BaseDialogue implements Serializable {
    private Long dialogueId;

    private Long dialogueFriendId;

    private Long dialogueMyselfId;

    private Long lastMessageId;

    private String lastMessageContent;

    private Long lastReadMessageId;

    private Date dialogueCreateTime;

    private Date dialogueUpdateTime;

    private Byte dialogueIsOnTop;

    private Date dialogueOnTopTime;

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

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }

    public Long getLastReadMessageId() {
        return lastReadMessageId;
    }

    public void setLastReadMessageId(Long lastReadMessageId) {
        this.lastReadMessageId = lastReadMessageId;
    }

    public Date getDialogueCreateTime() {
        return dialogueCreateTime;
    }

    public void setDialogueCreateTime(Date dialogueCreateTime) {
        this.dialogueCreateTime = dialogueCreateTime;
    }

    public Date getDialogueUpdateTime() {
        return dialogueUpdateTime;
    }

    public void setDialogueUpdateTime(Date dialogueUpdateTime) {
        this.dialogueUpdateTime = dialogueUpdateTime;
    }

    public Byte getDialogueIsOnTop() {
        return dialogueIsOnTop;
    }

    public void setDialogueIsOnTop(Byte dialogueIsOnTop) {
        this.dialogueIsOnTop = dialogueIsOnTop;
    }

    public Date getDialogueOnTopTime() {
        return dialogueOnTopTime;
    }

    public void setDialogueOnTopTime(Date dialogueOnTopTime) {
        this.dialogueOnTopTime = dialogueOnTopTime;
    }
}