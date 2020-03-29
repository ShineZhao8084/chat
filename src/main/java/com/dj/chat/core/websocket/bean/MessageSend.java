package com.dj.chat.core.websocket.bean;

import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueExtend;
import com.dj.chat.main.bean.BaseMessage;

import java.util.List;

/**
 * 服务端发送消息消息体
 * 识别码: 802
 * 客户端识别消息类型是802则进行消息展示
 */
public class MessageSend {

    private BaseMessage baseMessage;

    private BaseDialogue baseDialogue;

    private List<BaseDialogueExtend> baseDialogueExtendList;

    public BaseMessage getBaseMessage() {
        return baseMessage;
    }

    public void setBaseMessage(BaseMessage baseMessage) {
        this.baseMessage = baseMessage;
    }

    public BaseDialogue getBaseDialogue() {
        return baseDialogue;
    }

    public void setBaseDialogue(BaseDialogue baseDialogue) {
        this.baseDialogue = baseDialogue;
    }

    public List<BaseDialogueExtend> getBaseDialogueExtendList() {
        return baseDialogueExtendList;
    }

    public void setBaseDialogueExtendList(List<BaseDialogueExtend> baseDialogueExtendList) {
        this.baseDialogueExtendList = baseDialogueExtendList;
    }

}
