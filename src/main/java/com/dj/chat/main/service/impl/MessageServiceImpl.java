package com.dj.chat.main.service.impl;

import com.dj.chat.core.websocket.bean.MessageReceive;
import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueExtend;
import com.dj.chat.main.bean.BaseDialogueResponseWrapper;
import com.dj.chat.main.bean.BaseMessage;
import com.dj.chat.main.mapper.BaseDialogueMapper;
import com.dj.chat.main.mapper.BaseMessageMapper;
import com.dj.chat.main.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private BaseMessageMapper baseMessageMapper;

    @Resource
    private BaseDialogueMapper baseDialogueMapper;

    @Value("${pagehelper.chat-list-page-size}")
    private int chatListPageSize;


    @Override
    public BaseDialogueResponseWrapper onMessage(MessageReceive messageReceive) {

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

        baseMessageMapper.insertSelective(baseMessageSender);
        baseMessageMapper.insertSelective(baseMessageReceive);

        //更新发送人会话
        BaseDialogue baseDialogueSender = new BaseDialogue();
        baseDialogueSender.setDialogueMyselfId(messageReceive.getFromAccountId());
        baseDialogueSender.setDialogueFriendId(messageReceive.getToAccountId());
        baseDialogueSender.setDialogueUpdateTime(now);
        baseDialogueSender.setLastMessageId(baseMessageSender.getBaseMessageId());
        baseDialogueSender.setLastMessageContent(messageReceive.getContentText());
        baseDialogueMapper.updateByFromAccountIdAndToAccountId(baseDialogueSender);

        //接收人会话，不存在则创建
        BaseDialogue baseDialogueReceive = baseDialogueMapper.selectByFromAccountIdAndToAccountId(messageReceive.getFromAccountId(), messageReceive.getToAccountId());
        if (null == baseDialogueReceive) {
            BaseDialogue baseDialogue = new BaseDialogue();
            baseDialogue.setDialogueMyselfId(messageReceive.getToAccountId());
            baseDialogue.setDialogueFriendId(messageReceive.getFromAccountId());
            baseDialogue.setLastMessageId(baseMessageSender.getBaseMessageId());
            baseDialogue.setLastMessageContent(messageReceive.getContentText());
            baseDialogue.setDialogueCreateTime(now);
            baseDialogue.setDialogueUpdateTime(now);
            baseDialogue.setDialogueIsOnTop((byte) 0);
            baseDialogueMapper.insertSelective(baseDialogue);
        } else {
            baseDialogueReceive.setLastMessageId(baseMessageReceive.getBaseMessageId());
            baseDialogueReceive.setLastMessageContent(messageReceive.getContentText());
            baseDialogueReceive.setDialogueUpdateTime(now);
            baseDialogueMapper.updateByFromAccountIdAndToAccountId(baseDialogueReceive);
        }

        //查询会话列表返回
        BaseDialogueResponseWrapper baseDialogueResponseWrapper = new BaseDialogueResponseWrapper();
        List<BaseDialogueExtend> from = baseDialogueMapper.listAllMyDialogueByAccountId(messageReceive.getFromAccountId());
        List<BaseDialogueExtend> to = baseDialogueMapper.listAllMyDialogueByAccountId(messageReceive.getToAccountId());
        baseDialogueResponseWrapper.setFromBaseDialogueList(from);
        baseDialogueResponseWrapper.setToBaseDialogueList(to);
        baseDialogueResponseWrapper.setBaseMessage(baseMessageReceive);
        baseDialogueResponseWrapper.setBaseDialogue(baseDialogueReceive);
        return baseDialogueResponseWrapper;
    }

    @Override
    public PageInfo<BaseMessage> getMyMessageByDialogueId(Long dialogueId, int currentPage) {
        Date now = new Date();
        BaseDialogue baseDialogue = baseDialogueMapper.selectByPrimaryKey(dialogueId);
        baseDialogue.setLastReadMessageId(baseDialogue.getLastMessageId());
        baseDialogue.setDialogueUpdateTime(now);
        baseDialogueMapper.updateByPrimaryKeySelective(baseDialogue);

        PageHelper.startPage(currentPage, chatListPageSize);
        List<BaseMessage> baseMessageList = baseMessageMapper.findMessagesByDialogue(baseDialogue);
        return new PageInfo<BaseMessage>(baseMessageList);
    }
}
