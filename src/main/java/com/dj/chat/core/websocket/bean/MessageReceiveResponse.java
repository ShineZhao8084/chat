package com.dj.chat.core.websocket.bean;

import com.dj.chat.main.bean.BaseDialogueExtend;

import java.util.List;

/**
 * 服务端接收发送消息返回回执消息体
 * 识别码: 801
 * 客户端识别消息类型是801则进行会话视图更新
 */
public class MessageReceiveResponse {

    private List<BaseDialogueExtend> baseDialogueExtendList;

    public List<BaseDialogueExtend> getBaseDialogueExtendList() {
        return baseDialogueExtendList;
    }

    public void setBaseDialogueExtendList(List<BaseDialogueExtend> baseDialogueExtendList) {
        this.baseDialogueExtendList = baseDialogueExtendList;
    }

}
