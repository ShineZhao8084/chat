package com.dj.chat.main.service;

import com.dj.chat.core.websocket.bean.MessageReceive;
import com.dj.chat.main.bean.BaseDialogueResponseWrapper;
import com.dj.chat.main.bean.BaseMessage;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MessageService {

    BaseDialogueResponseWrapper onMessage(MessageReceive messageReceive);

    PageInfo<BaseMessage> getMyMessageByDialogueId(Long dialogueId, int currentPage);
}
