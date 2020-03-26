package com.dj.chat.main.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseMessage;
import com.dj.chat.main.mapper.BaseDialogueMapper;
import com.dj.chat.main.mapper.BaseMessageMapper;
import com.dj.chat.main.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private BaseMessageMapper baseMessageMapper;
    @Resource
    private BaseDialogueMapper baseDialogueMapper;

    @Override
    public void onMessage(JSONObject jsonObject) {
        Long fromAccountId = Long.valueOf(jsonObject.getString("fromAccountId"));
        Long toAccountId = Long.valueOf(jsonObject.getString("toAccountId"));
        String contentText = jsonObject.getString("contentText");

        Date now = new Date();
        BaseMessage baseMessageMe = new BaseMessage();
        baseMessageMe.setBaseMessageBelong(fromAccountId);
        baseMessageMe.setBaseMessageFrom(fromAccountId);
        baseMessageMe.setBaseMessageTo(toAccountId);
        baseMessageMe.setBaseMessageSendTime(now);
        baseMessageMe.setBaseMessageContent(contentText);
        baseMessageMapper.insert(baseMessageMe);

        BaseMessage baseMessageTo = new BaseMessage();
        baseMessageTo.setBaseMessageBelong(toAccountId);
        baseMessageTo.setBaseMessageFrom(fromAccountId);
        baseMessageTo.setBaseMessageTo(toAccountId);
        baseMessageTo.setBaseMessageSendTime(now);
        baseMessageTo.setBaseMessageContent(contentText);
        baseMessageMapper.insert(baseMessageTo);

        //更新我的会话状态，会话表修改count字段为最后消息来源字段，添加会话创建时间和更新时间用作前端排序字段，添加置顶字段和置顶时间字段
        BaseDialogue baseDialogue = new BaseDialogue();
        //List<BaseDialogue> dialogueList = baseDialogueMapper.selectABaseDialogue();

        //对方会话，不存在则创建，根据对方在线状态决定是否发送对方的接收报文

        //发送接收消息时，重新渲染会话视图（重新顺序） 重新设计websocket交互报文

        //（我方发送）发送报文：来源，去向，消息类型，消息内容
        //（处理事件）更新我的会话，对方会话

        //（我方接收）发送回执报文：我的会话列表

        //（对方接收）接收报文：来源，去向，消息类型，消息内容，会话列表

        //（对方发送）接收回执报文：已读消息类型（201：在线且当前窗口显示 201：在线不在当前窗口）
        //（处理事件）更新对方会话，我的会话

        //前端会话列表点击事件：更新我的会话，对方会话，返回消息列表
    }
}
