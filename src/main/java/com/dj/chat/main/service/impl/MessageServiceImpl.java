package com.dj.chat.main.service.impl;

import com.dj.chat.core.websocket.bean.MessageReceive;
import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseMessage;
import com.dj.chat.main.mapper.BaseDialogueMapper;
import com.dj.chat.main.mapper.BaseMessageMapper;
import com.dj.chat.main.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private BaseMessageMapper baseMessageMapper;
    @Resource
    private BaseDialogueMapper baseDialogueMapper;

    @Override
    public Map<String, List<BaseDialogue>> onMessage(MessageReceive messageReceive) {

        Date now = new Date();

        //消息入库
        BaseMessage baseMessageSender = new BaseMessage();  //发送者消息保存
        baseMessageSender.setBaseMessageBelong(messageReceive.getFromAccountId());
        baseMessageSender.setBaseMessageFrom(messageReceive.getFromAccountId());
        baseMessageSender.setBaseMessageTo(messageReceive.getToAccountId());
        baseMessageSender.setBaseMessageTypeId(1);
        baseMessageSender.setBaseMessageContent(messageReceive.getContentText());
        baseMessageSender.setBaseMessageSendTime(now);

        BaseMessage baseMessageReceive = new BaseMessage();  //接收者消息保存
        baseMessageReceive.setBaseMessageBelong(messageReceive.getToAccountId());
        baseMessageReceive.setBaseMessageFrom(messageReceive.getFromAccountId());
        baseMessageReceive.setBaseMessageTo(messageReceive.getToAccountId());
        baseMessageReceive.setBaseMessageTypeId(1);
        baseMessageReceive.setBaseMessageContent(messageReceive.getContentText());
        baseMessageReceive.setBaseMessageSendTime(now);

        baseMessageMapper.insert(baseMessageSender);
        baseMessageMapper.insert(baseMessageReceive);

        //更新发送人会话、接收人会话
        BaseDialogue baseDialogueSender = new BaseDialogue();
        baseDialogueSender.getDialogueFriendId();

        BaseDialogue baseDialogueReceive = new BaseDialogue();

        
        baseDialogueMapper.updateByPrimaryKeySelective(baseDialogueSender);

        return null;
    }
}
